package reqres.io;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PutPatchDeleteExamples {

    @BeforeTest
    public static void setup() {
        //Specify base URI
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testPut() {

        JSONObject request = new JSONObject();

        request.put("name", "Collin");
        request.put("job", "Accountant");

        System.out.println(request.toJSONString());

        Response response = given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("users/2")
                .then()
                .statusCode(200)
                .extract().response();


    }

    @Test
    public void testPatch() {

        JSONObject request = new JSONObject();

        request.put("name", "Collin");
        request.put("job", "Accountant");

        System.out.println(request.toJSONString());

        Response response = given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("users/2")
                .then()
                .statusCode(200)
                .extract().response();


    }

    @Test
    public void testDelete() {

        Response response = when()
                .delete("users/2")
                .then()
                .statusCode(204)
                .extract().response();


    }
}
