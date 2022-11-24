package api.github;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.IsEmptyString.emptyString;

public class ValidatableResponse {

    public static final String BASE_URI = "https://api.github.com/";

    Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
            "access-control-allow-origin", "*");

    @Test
    public void basicValidatableResponse() {
        RestAssured.get(BASE_URI)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().assertThat()
                .header("x-ratelimit-limit", "60");
    }

    @Test
    public void simpleHamcrestMatchers() {
        RestAssured.get(BASE_URI)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(300))
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .header("etag", Matchers.notNullValue())
                .header("etag", Matchers.not(emptyString()));
    }

    @Test
    public void complexHamcrestMatchers() {
        RestAssured.get(BASE_URI)
                .then()
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("x-ratelimit-limit",
                        response -> greaterThan(response.header("x-ratelimit-remaining")))
                .headers(expectedHeaders);
    }
}
