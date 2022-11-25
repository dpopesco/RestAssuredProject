package automation.exercises;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.given;

public class GetRequests {

    @BeforeTest
    public static void setup() {
        //Specify base URI
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void getProducts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/productsList")
                .then()
                .extract().response();

        //response content type is html and we need to convert to json
        JsonPath jsonPath = response.jsonPath();
        List<Product> products = jsonPath.getList("products", Product.class);

        Assert.assertEquals(products.get(28).getId(), 38);
        Assert.assertEquals(products.get(22).getName(), "Green Side Placket Detail T-Shirt");
        Assert.assertEquals(products.get(23).getPrice(), "Rs. 1500");
        Assert.assertEquals(products.get(29).getBrand(), "Biba");
        Assert.assertEquals(products.get(32).getCategory().getCategory(), "Tops");
        Assert.assertTrue(products.stream().map(x -> x.getName()).toList()
                .containsAll(Arrays.asList("Madame Top For Women", "Full Sleeves Top Cherry - Pink", "Blue Cotton Indie Mickey Dress")));


        Assert.assertEquals(200, response.getStatusCode());

        ResponseBody body = response.getBody();

        System.out.println("Response body is: " + body.asString());
        System.out.println("Total number of products: " + response.body().jsonPath().getInt("products.size()"));
    }

    @Test
    public void getBrands() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("brandsList")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.getStatusCode());

        ResponseBody body = response.getBody();

        System.out.println("Response body is: " + body.asString());
    }

    @Test
    public void getUserAccountDetailByEmail() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .queryParam("email", "ddd@sample.com")
                .get("getUserDetailByEmail")
                .then()
                .extract().response();
        Assert.assertEquals(200, response.getStatusCode());

        ResponseBody body = response.getBody();
        System.out.println("Response body is: " + body.asString());

    }
}
