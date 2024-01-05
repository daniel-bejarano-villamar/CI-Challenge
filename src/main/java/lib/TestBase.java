package lib;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static lib.Constants.ENDPOINT;
import static lib.Constants.NOTFOUND_GET_STATUS_CODE;

public class TestBase {
    protected Response callToCharacterTwo() {
        //Variable definition
        String endpointCharacter2 = ENDPOINT + "/people/2/";

        //Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        Response characterResponse = given().headers(headers).when().get(endpointCharacter2);

        validateSuccessStatusCode(characterResponse.getStatusCode());
        return characterResponse;
    }

    protected JsonPath toJsonPath (Response characterResponse) {
        String characterResponseString = characterResponse.getBody().asString();
        JsonPath js = new JsonPath(characterResponseString);
        return js;
    }

    protected String getCharacterColor(Response characterResponse) {
        JsonPath js = toJsonPath((characterResponse));

        return js.get("skin_color");
    }

    protected int getNumberOfFilmsCharacterIsIn(Response characterResponse) {
        JsonPath js = toJsonPath((characterResponse));
        List<String> films = js.getList("films");

        return films.size();
    }

    protected void validateSuccessStatusCode(int receivedStatusCode){
        Assert.assertEquals(Constants.SUCCESS_GET_STATUS_CODE, receivedStatusCode);
    }

    protected Response callToFilmSeven() {
        // Variable definition
        String endpoint = "https://swapi.dev/api/films/7/";

        // Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        // Get the response
        Response response = given().headers(headers).when().get(endpoint);

        return response;
    }

    protected void validateNotFoundStatusCode(int receivedStatusCode) {
        Assert.assertEquals(receivedStatusCode, NOTFOUND_GET_STATUS_CODE);
    }

    protected Response callToFilm(String filmUrl) {
        // Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        // Get the response
        Response filmResponse = given().headers(headers).when().get(filmUrl);

        validateSuccessStatusCode(filmResponse.getStatusCode());
        return filmResponse;
    }

    protected void validateDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            Assert.fail("Date is not in the correct format", e);
        }
    }

    protected void validateListSize(List<String> list, int expectedSize) {
        Assert.assertTrue(list.size() > expectedSize, "List does not include more than " + expectedSize + " items");
    }
}
