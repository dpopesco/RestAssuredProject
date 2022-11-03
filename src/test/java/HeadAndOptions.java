import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

public class HeadAndOptions {

    public static final String BASE_URI = "https://api.github.com/";

    @Test
    public void headTest() {
        RestAssured.head(BASE_URI)
                .then()
                .statusCode(200)
                .body(emptyOrNullString());
    }

    @Test
    public void optionsTest() {
        RestAssured.options(BASE_URI)
                .then()
                .statusCode(204)
                .header("access-control-allow-methods", equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(emptyOrNullString());
    }
}
