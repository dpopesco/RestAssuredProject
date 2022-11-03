import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PostPatchDeleteMethods {

    public static final String BASE_URI = "https://api.github.com/user/repos";
    public static final String TOKEN = "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";

    @Test(description = "Create a repo")
    public void post() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .post(BASE_URI)
                .then()
                .statusCode(201);
    }

    @Test(description = "Update a repo")
    public void patch() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
                .when()
                .patch("https://api.github.com/repos/dpopesco/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(description = "Delete a repo", dependsOnMethods = "post")
    public void delete() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .when()
                .delete("https://api.github.com/repos/dpopesco/deleteme-patched")
                .then()
                .statusCode(204);
    }
}
