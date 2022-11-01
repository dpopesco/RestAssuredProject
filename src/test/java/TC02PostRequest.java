import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02PostRequest {
    @Test
    void successfulRegistration() {
        //Specify base URI
        RestAssured.baseURI = "https://demoqa.com/Account/v1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParam = new JSONObject();

        requestParam.put("userName", "nepl12333");
        requestParam.put("password", "Quaddddddddd17!");


        // Add a header stating the Request body is a JSON
        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParam.toJSONString());

        //Response object
        Response response = httpRequest.post("/User");

        //print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 201);

        //status line verification
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
    }
}
