package steps;

import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import model.Location;
import request.HttpMethod;
import service.RestfulApiService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationSteps {
    private ScenarioContext scenarioContext;

    public LocationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Then("I should see the street name as {string}")
    public void assertStreetName(String name) {
        Type type = new TypeToken<List<Location>>() {
        }.getType();
        List<Location> locations = scenarioContext.get(Constants.RESPONSE, Response.class)
                .getBody().as(type, ObjectMapperType.GSON);
        assertThat(locations.get(0).getAddress().get(0).getStreet(), equalTo(name));
        assertThat(locations.get(0).getAddress().get(1).getStreet(), equalTo(name));
    }

    @When("I request for location using {string}, {string}, {int}")
    public void requestForLocation(String path, String locationIdKey, Integer locationIdValue) {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put(locationIdKey, locationIdValue);
        String token = scenarioContext.get(Constants.TOKEN, String.class);
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
                .setBasePath(path)
                .addQueryParams(queryParams)
                .addHeader(Constants.AUTHORIZATION, Constants.BEARER + " "  + token)
                .build();
        Response response = RestfulApiService.send(builder, HttpMethod.GET);
        scenarioContext.put(Constants.RESPONSE, response);
    }
}