package api.github;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC01GetRequest {
    @BeforeTest
    public static void setup() {
        //Specify base URI
        RestAssured.baseURI = "https://api.open-meteo.com/v1/forecast";
    }

    @Test
    void getWeatherDetails() {

        RequestSpecification httpRequest = RestAssured.given()
                .param("latitude", 52.52)
                .param("longitude", 13.41)
                .param("hourly", "temperature_2m");

        Response response = httpRequest.request(Method.GET);
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //status line verification
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
}
