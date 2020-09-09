package steps;

import com.google.gson.reflect.TypeToken;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Location;
import model.Post;
import model.UserLoginDetails;
import request.HttpMethod;
import service.AuthenticationService;
import service.RestfulApiService;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RequestPostsSteps {
    private ScenarioContext scenarioContext;

    public RequestPostsSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I perform authentication operation for {string} with body")
    public void authenticate(String path, DataTable dataTable) {
        String email = dataTable.row(1).get(0);
        String password = dataTable.row(1).get(1);
        String token = new AuthenticationService().authenticate(path, new UserLoginDetails(email, password));
        assertThat(token, is(notNullValue()));
        scenarioContext.put("token", token);
    }

    @When("I request for {string}")
    public void request(String path) {
        String token = scenarioContext.get("token", String.class);
        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.GET)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();
        scenarioContext.put("responseOptions", responseOptions);
    }

    @Then("I should see first two author name as {string}")
    public void assertFirstTwoAuthorNames(String authorName) {
        Type type = new TypeToken<List<Post>>() {
        }.getType();
        List<Post> posts = scenarioContext.get("responseOptions", ResponseOptions.class)
                .getBody().as(type, ObjectMapperType.GSON);
        assertThat(posts.get(0).getAuthor(), equalTo(authorName));
        assertThat(posts.get(1).getAuthor(), equalTo(authorName));
    }

    @Then("I should see author name as {string}")
    public void assertAuthorName(String authorName) {
        Post post = scenarioContext.get("responseOptions", ResponseOptions.class).getBody().as(Post.class);
        assertThat(post.getAuthor(), equalTo(authorName));
    }

    @Then("the response from the retrieve {string} should be in the correct format")
    public void assertInCorrectFormat(String response) {
        ResponseOptions retrieveResponseOption = scenarioContext.get("responseOptions", ResponseOptions.class);
        String body = retrieveResponseOption.getBody().asString();
        assertThat(body, matchesJsonSchemaInClasspath("schemas/" + response + ".json"));
    }

    @Then("I should see the street name as {string}")
    public void assertStreetName(String name) {
        Type type = new TypeToken<List<Location>>() {
        }.getType();
        List<Location> locations = scenarioContext.get("responseOptions", ResponseOptions.class)
                .getBody().as(type, ObjectMapperType.GSON);
        assertThat(locations.get(0).getAddress().get(0).getStreet(), equalTo(name));
        assertThat(locations.get(0).getAddress().get(1).getStreet(), equalTo(name));
    }
}