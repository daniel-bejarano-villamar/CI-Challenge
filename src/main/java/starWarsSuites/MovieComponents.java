package starWarsSuites;

import io.restassured.response.Response;
import lib.TestBase;
import org.testng.annotations.Test;

import java.util.List;

public class MovieComponents extends TestBase {

    @Test(description = "Evaluate the color of the character")
    public void testMovieComponents() {
        // Call to character two and get the response
        Response characterResponse = callToCharacterTwo();

        // Get the details of the second film
        List<String> films = characterResponse.jsonPath().getList("films");
        Response filmResponse = callToFilm(films.get(1));

        // Check that the release date is in the correct format
        String releaseDate = filmResponse.jsonPath().getString("release_date");
        validateDate(releaseDate);

        // Check that the response includes characters, planets, starships, vehicles and species
        validateListSize(filmResponse.jsonPath().getList("characters"), 1);
        validateListSize(filmResponse.jsonPath().getList("planets"), 1);
        validateListSize(filmResponse.jsonPath().getList("starships"), 1);
        validateListSize(filmResponse.jsonPath().getList("vehicles"), 1);
        validateListSize(filmResponse.jsonPath().getList("species"), 1);
    }
}
