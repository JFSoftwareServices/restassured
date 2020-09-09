package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Profile;
import request.HttpMethod;
import service.RestfulApiService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateProfileSteps {
    private ScenarioContext scenarioContext;

    public UpdateProfileSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I update profile at uri {string} to name {string}")
    public void updateProfile(String path, String name) {
        String token = scenarioContext.get("token", String.class);

        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.POST)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .setBody(Profile.builder().name(name).build())
                .send();

        scenarioContext.put("responseOptions", responseOptions);
    }

    @Then("I should see profile name as {string}")
    public void assertProfileName(String name) {
        Profile profile = scenarioContext.get("responseOptions", ResponseOptions.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
    }
}