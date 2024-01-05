package starWarsSuites;

import io.restassured.response.Response;
import lib.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Color extends TestBase {

    @Test(description = "Evaluate the color of the character")
    public void testColor() {
        // Call to character two and get the response
        Response characterResponse = callToCharacterTwo();

        // Get the skin color and check it is gold
        String skinColor = getCharacterColor(characterResponse);
        Assert.assertEquals(skinColor, "gold", "Skin color is not gold");

        // Get the number of films character is in and check it is 6
        int numberOfFilms = getNumberOfFilmsCharacterIsIn(characterResponse);
        Assert.assertEquals(numberOfFilms, 6, "Character does not appear in 6 films");
    }
}
