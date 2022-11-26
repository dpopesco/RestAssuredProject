package automation.exercises;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequest {

    @BeforeTest
    public static void setup() {
        //Specify base URI
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void postUserAccount() {

        JSONObject request = new JSONObject();


        Response response = given()
                .formParam("name", "Daria")
                .formParam("email", "dddd@sample.com")
                .formParam("password", "Quatro")
                .formParam("firstname", "Daria")
                .formParam("lastname", "Boris")
                .formParam("address1", "Kentuky")
                .formParam("country", "United States")
                .formParam("zipcode", "30405")
                .formParam("state", "Kentucky")
                .formParam("city", "Luisville")
                .formParam("mobile_number", "09098457347")
                .when()
                .post("/createAccount")
                .then()
                .extract().response();

        ResponseBody body = response.getBody();
        Assert.assertTrue(body.asString().contains("201"));
    }
}
