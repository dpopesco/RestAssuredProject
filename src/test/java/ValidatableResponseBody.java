import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class ValidatableResponseBody {

    public static final String BASE_URI = "https://api.github.com/";

    @Test
    public void matcherExample() {

        RestAssured.get(BASE_URI)
                .then()
                .body("current_user_url", equalTo(BASE_URI + "user"))
                .body(containsString("feeds_url"))
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }
}
