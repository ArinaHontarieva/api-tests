package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class CrudTests extends BaseTest {

    @Test
    @DisplayName("Create user (POST)")
    void testCreateUser() {
        String newUser = """
            {
              "name": "Test User",
              "username": "tester",
              "email": "test@example.com"
            }
            """;

        RestAssured
            .given().contentType(ContentType.JSON).body(newUser)
            .when().post(BASE_URL)
            .then().statusCode(201)
            .body("name", equalTo("Test User"))
            .body("email", equalTo("test@example.com"));
    }

    @Test
    @DisplayName("Read user (GET)")
    void testReadUser() {
        RestAssured
            .given()
            .when().get(BASE_URL + "/1")
            .then().statusCode(200).body("id", equalTo(1));
    }

    @Test
    @DisplayName("Update user (PUT)")
    void testUpdateUser() {
        String updatedUser = """
            {
              "id": 1,
              "name": "Updated User",
              "username": "updated",
              "email": "updated@example.com"
            }
            """;

        RestAssured
            .given().contentType(ContentType.JSON).body(updatedUser)
            .when().put(BASE_URL + "/1")
            .then().statusCode(200)
            .body("name", equalTo("Updated User"));
    }

    @Test
    @DisplayName("Delete user (DELETE)")
    void testDeleteUser() {
        RestAssured
            .given()
            .when().delete(BASE_URL + "/1")
            .then().statusCode(200);
    }
}
