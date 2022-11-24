package automation.exercises;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
