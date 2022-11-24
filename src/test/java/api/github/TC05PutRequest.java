package api.github;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05PutRequest {
    @Test
    public void putRequest() {
        RestAssured.baseURI = "https://demoqa.com";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFuaXRhdGF0YSIsInBhc3N3b3JkIjoiTWlsbzEyMzQ1NiEiLCJpYXQiOjE2NjczMjM2NTd9.xEATcnPUm8L8J4CbYd_aVY6FoP1bOl_AJ9uz6uLCUa4";
        String isbn = "9781593277574";
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        //Calling the PUT API with request body
        Response res = httpRequest.body("{ \"isbn\": \"" + isbn + "\", \"userId\": \"5e5a59f7-652d-4d55-a00c-7b8ff28abd1f\"}")
                .put("/BookStore/v1/Books/9781449325862");

        //Fetching the response code from the request and validating the same
        System.out.println("The response code - " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
