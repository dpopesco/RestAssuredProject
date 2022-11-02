import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class BasicResponseBody {
    public static final String BASE_URI = "https://api.github.com/rate_limit";

    @Test
    public void jsonPathReturnsMap() {
        Response response = RestAssured.get(BASE_URI);
        ResponseBody<?> body = response.body();
        JsonPath jsonPath = body.jsonPath();

        JsonPath jsonPath2 = response.body().jsonPath();

        Map<String, String> fullJson = jsonPath2.get();
        Map<String, String> subMap = jsonPath2.get("resources");
        Map<String, String> subMap2 = jsonPath2.get("resources.core");

        int value = jsonPath.get("resources.core.limit");
        int value2 = jsonPath.get("resources.graphql.remaining");

        System.out.println(fullJson);
        System.out.println(subMap);
        System.out.println(subMap2);
        System.out.println(value);
        System.out.println(value2);
        assertEquals(value, 60);
        assertEquals(value2, 0);
    }
}
