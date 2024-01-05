package starWarsSuites;

import io.restassured.response.Response;
import lib.TestBase;
import org.testng.annotations.Test;

public class FilmNotFound extends TestBase {

    @Test(description = "Evaluate film not found")
    public void testFilmNotFound() {
        // Call to film seven and get the response
        Response response = callToFilmSeven();

        // Validate the status code is 404
        validateNotFoundStatusCode(response.getStatusCode());
    }
}
