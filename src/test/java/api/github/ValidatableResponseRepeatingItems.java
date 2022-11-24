package api.github;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class ValidatableResponseRepeatingItems {

    public static final String BASE_URI = "https://reqres.in/api/users?page=1";
    @Test
    public void repeatingItems(){
        RestAssured.get(BASE_URI)
                .then()
                .body("data.id[0]",equalTo(1))
                .body("data.id[1]",equalTo(2))
                .body("data.email[1]",equalTo("janet.weaver@reqres.in"))
                .body("data.email[2]",equalTo("emma.wong@reqres.in"))
                .body("data.first_name",hasItems("George","Janet"));
    }
}
