package tests;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static String BASE_URL;

    //This format was added to fulfill the conditions of the next task with Jenkins
    @BeforeAll
    static void setup() {
        BASE_URL = System.getProperty("baseUrl", "https://jsonplaceholder.typicode.com/users");
    }
}
