package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.Profile;
import request.HttpMethod;
import service.RestfulApiService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProfileSteps {
    private ScenarioContext scenarioContext;

    public ProfileSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I create a profile using {string} with {string} and {int}")
    public void createProfile(String path, String name, Integer id) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.POST)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .setBody(Profile.builder().name(name).postId(id).build())
                .send();
        scenarioContext.put("response", response);
    }

    @Then("I should see the new profile has name {string} and {int}")
    public void assertProfile(String name, Integer id) {
        Profile profile = scenarioContext.get("response", Response.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
        assertThat(profile.getPostId(), equalTo(id));
    }

    @When("I update profile at uri {string} to name {string}")
    public void updateProfile(String path, String name) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.POST)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .setBody(Profile.builder().name(name).build())
                .send();
        scenarioContext.put("response", response);
    }

    @Then("I should see profile name as {string}")
    public void assertProfileName(String name) {
        Profile profile = scenarioContext.get("response", Response.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
    }
}