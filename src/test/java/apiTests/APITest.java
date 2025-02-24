package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITest {

    @Test
    public void testGetRequest() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2");

        response.prettyPrint(); // Print response for debugging

        Assert.assertEquals(response.getStatusCode(), 200); // Validate status code
    }
}
