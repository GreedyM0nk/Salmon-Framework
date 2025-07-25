package com.salmon.test.step_definitions.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */
public class ApiSteps {

    private Response response;

    /**
     * Perform a HTTP GET request for an endpoint.
     */
    @When("^I perform GET request for \"([^\"]*)\" endpoint$")
    public void iPerformGETRequestForEndpoint(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .accept(ApiHelper.APPLICATION_JSON)
                .when()
                .get(UrlBuilder.getApiUrlForEndPoint(endpoint));
    }

    /**
     * Verify HTTP Status code from response.
     */
    @Then("^I get a (\\d+) http status code$")
    public void iGetAHttpStatusCode(int statusCodeExpected) {
        Assert.assertEquals(response.statusCode(), statusCodeExpected, "Unexpected HTTP status code");
    }

    /**
     * Example with JsonPath to extract names of colour from JSON response.
     */
    @Then("^the colour collections contains colour name$")
    public void theColourCollectionsContainsColourName() {
        List<String> colourNames = from(response.asString()).getList("colors.name");
        Assert.assertNotNull(colourNames, "Colour names list should not be null");
        Assert.assertTrue(colourNames.size() > 0, "Colour names list should not be empty");
    }
}