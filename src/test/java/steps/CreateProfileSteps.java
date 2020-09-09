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

public class CreateProfileSteps {

    private ScenarioContext scenarioContext;

    public CreateProfileSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I post to {string} with {string} and {int}")
    public void icreateProfile(String path, String name, int postId) {
        String token = scenarioContext.get("token", String.class);

        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.POST)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .setBody(Profile.builder().name(name).postId(postId).build())
                .send();

        scenarioContext.put("responseOptions", responseOptions);
    }

    @Then("I should see the new profile has name {string} and {int}")
    public void assertCreatedProfile(String name, int id) {
        Profile profile = scenarioContext.get("responseOptions", ResponseOptions.class).getBody().as(Profile.class);
        assertThat(profile.getName(), equalTo(name));
        assertThat(profile.getPostId(), equalTo(id));
    }
}