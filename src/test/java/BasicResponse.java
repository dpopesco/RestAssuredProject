import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicResponse {

    public static final String BASE_URI = "https://api.github.com/";

    @Test
    public void peek() {
        RestAssured.get(BASE_URI)
                .peek();
    }

    @Test
    public void prettyPeek() {
        RestAssured.get(BASE_URI)
                .prettyPeek();

    }

    @Test
    public void convenienceMethods() {
        Response response = RestAssured.get(BASE_URI);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void genericHeader() {
        Response response = RestAssured.get(BASE_URI);

        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
    }

    @Test
    public void getHeaders() {
        Response response = RestAssured.get(BASE_URI);
        Headers headers = response.getHeaders();
        String val = headers.getValue("header1");
        int size = headers.size();
        List<Header> list = headers.asList();
        boolean isPresent = headers.hasHeaderWithName("header2");
        assertTrue(isPresent);
    }

    @Test
    public void timeResponse() {
        Response response = RestAssured.get(BASE_URI);
        response.getTime();
        System.out.println(response.getTimeIn(TimeUnit.MINUTES));
    }
}
