package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyAPITest {
    String token = "BQCsYuPH9WNGAp7a9Xfz_R5EQ8BfzI1HfHTcGVFZmcvLOJqDcOg6rHNEkWb026jhlDE1pb1Y2YEXhRhF6G03xWg1UFMQKZ3huymlTnksC1RO1uaQ9G2zat_HmTtrIR5atg6Nu6AqOH1VS0-2fgiZ4R2OoeFZ65kyRM8tn-xA1r6NT3NbRdxiEqD88DsLthDWvXz0m2lsTvJB6nECvyPUiXB2GXM-EnRDpCWeU9RAlU1d6QWsxwidjamyf4ySG9fh5Z3b0HmHQ4ocMGGuFVTZ1ocTmHHjpLOmhucOiGes_4Z0bKKXqQhio9TVBjh9M_b3I2I-SXxBP4tr9jT9Q47a";
    String playlistId = "3cEYpjA9oz9GiPac4AsH4n";
    @Test
    public void getCurrentUserProfile() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
    }
    @Test
    public void getUserTopItems() {

        Response response = given()
                .header("Authorization", "Bearer " + token) // Replace with the token from Postman
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("https://api.spotify.com/v1/me/top/artists");

        // Print response
        response.prettyPrint();

        // Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch top artists");
    }
    @Test
    public void followPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{ \"public\": true }")  // Set to "false" for a private follow
                .when()
                .put("https://api.spotify.com/v1/playlists/" + playlistId + "/followers");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to follow playlist");
        System.out.println("✅ Followed Playlist Successfully");
    }

    @Test
    public void unfollowPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/followers");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to unfollow playlist");
        System.out.println("✅ Unfollowed Playlist Successfully");
    }

}
