package localhost;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DataDrivenExamples extends DataForTests {

    @BeforeTest
    public static void setup() {
        //Specify base URI
        baseURI = "http://localhost:3000/";
    }

    @Test(dataProvider = "DataForPost")
    public void postRequest(String firstName, String lastName, int subjectId) {
        JSONObject request = new JSONObject();

        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();

    }

    @Test(dataProvider = "Delete Data")
    public void deleteRequest(int userId) {

        when()
                .delete("/users/" + userId)
                .then()
                .statusCode(200);

    }

    @Parameters({"userId"})
    @Test(dataProvider = "Delete Data")
    public void anotherDeleteRequest(int userId) {

        System.out.println("Value for userId is: " + userId);

        when()
                .delete("/users/" + userId)
                .then()
                .statusCode(200);

    }
}
