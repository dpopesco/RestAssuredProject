package localhost;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetPostPutDeleteRequests {

    @BeforeTest
    public static void setup() {
        //Specify base URI
        baseURI = "http://localhost:3000/";
    }

    @Test
    public void getRequest() {


        given()
                .param("name", "Automation")
                .get("/subjects")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void postRequest() {
        JSONObject request = new JSONObject();

        request.put("firstName", "Tina");
        request.put("lastName", "Jolie");
        request.put("subjectId", 1);

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

    @Test
    public void patchRequest() {
        JSONObject request = new JSONObject();

        request.put("lastName", "Larkson");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .patch("/users/4")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void putRequest() {
        JSONObject request = new JSONObject();

        request.put("firstName", "Eloriana");
        request.put("lastName", "Milo");
        request.put("subjectId", 1);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .put("/users/4")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void deleteRequest() {

        when()
                .delete("/users/4")
                .then()
                .statusCode(200);

    }
}
