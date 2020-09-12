package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import model.Profile;
import request.HttpMethod;
import service.RestfulApiService;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProfileSteps {
    private ScenarioContext scenarioContext;

    public ProfileSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I create a profile using {string}, {string}, {int}, {string}")
    public void createProfile(String path, String postIdKey, Integer postIdValue, String name) {
        String token = scenarioContext.get(Constants.TOKEN, String.class);
        Map<String, Integer> params = new HashMap<>();
        params.put(postIdKey, postIdValue);
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(path)
                .addHeader(Constants.AUTHORIZATION, Constants.BEARER + " "  + token)
                .addPathParams(params)
                .setBody(Profile.builder().name(name).postId(postIdValue).build())
                .build();
        Response response = RestfulApiService.send(builder, HttpMethod.POST);
        scenarioContext.put(Constants.RESPONSE, response);
    }

    @Then("the new profile has name {string} and postId {int}")
    public void assertProfile(String name, Integer postId) {
        Profile profile = scenarioContext.get(Constants.RESPONSE, Response.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
        assertThat(profile.getPostId(), equalTo(postId));
    }

    @When("I update profile at uri {string} to name {string}")
    public void updateProfile(String path, String name) {
        String token = scenarioContext.get(Constants.TOKEN, String.class);
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBasePath(path)
                .addHeader(Constants.AUTHORIZATION, Constants.BEARER + " "  + token)
                .setBody(Profile.builder().name(name).build())
                .build();
        Response response = RestfulApiService.send(builder, HttpMethod.POST);
        scenarioContext.put(Constants.RESPONSE, response);
    }

    @Then("I should see profile name as {string}")
    public void assertProfileName(String name) {
        Profile profile = scenarioContext.get(Constants.RESPONSE, Response.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
    }
}