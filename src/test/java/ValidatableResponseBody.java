import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
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

    @Test
    public void nestedBodyValidation() {
        RestAssured.get(BASE_URI + "rate_limit")
                .then()
                .rootPath("resources.core")
                    .body("limit", equalTo(60))
                    .body("remaining", lessThanOrEqualTo(60))
                    .body("reset", notNullValue())
                .rootPath("resources.search")
                    .body("limit", equalTo(10))
                    .body("remaining", lessThanOrEqualTo(10))
                .noRootPath()
                    .body("resources.graphql.limit", is(0));
    }
}
