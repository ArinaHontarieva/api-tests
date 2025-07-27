package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiTests  extends BaseTest {

    @Test
    @DisplayName("Test to verify an http status code")
    void testStatusCodeIs200() {
        RestAssured
            .given()
            .when().get(BASE_URL)
            .then().statusCode(200);
    }

    @Test
    @DisplayName("Test to verify an http response header")
    void testContentTypeHeader() {
        Response response = RestAssured.get(BASE_URL);
        String contentType = response.getHeader("Content-Type");

        assertThat("Header exists", contentType, notNullValue());
        assertThat("Header is application/json", contentType, equalTo("application/json; charset=utf-8"));
    }

    @Test
    @DisplayName("Test to verify an http response body:")
    void testResponseBodyContains10Users() {
        RestAssured
            .given()
            .when().get(BASE_URL)
            .then().assertThat().contentType(ContentType.JSON).body("size()", equalTo(10));
    }
}
