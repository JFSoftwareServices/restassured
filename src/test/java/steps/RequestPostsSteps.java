package steps;


import com.google.gson.reflect.TypeToken;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Post;
import request.HttpMethod;
import service.RestfulApiService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestPostsSteps {
    private ScenarioContext scenarioContext;

    public RequestPostsSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I request for {string}")
    public void requestPost(String path) {
        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setHttpMethod(HttpMethod.GET)
                .send();
        scenarioContext.put("responseOptions", responseOptions);
    }

    @Given("I request for {string} with pathParameters:")
    public void requestForPostWithPathParam(String path, DataTable dataTable) {
        Map<String, String> parameterNameValuePairs = dataTable.asMap(String.class, String.class);
        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setPathParams(parameterNameValuePairs)
                .setBasePath(path + "/{" + parameterNameValuePairs.keySet().iterator().next() + "}")
                .setHttpMethod(HttpMethod.GET)
                .send();
        scenarioContext.put("responseOptions", responseOptions);
    }

    @Then("I should see first two author name as {string}")
    public void assertFirstTwoAuthorNamesor_name_as(String authorName) {
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
    public void assertResponseIsInCorrectFormat(String response) {
        ResponseOptions retrieveResponseOption = scenarioContext.get("responseOptions", ResponseOptions.class);
        String body = retrieveResponseOption.getBody().asString();
        assertThat(body, matchesJsonSchemaInClasspath("schemas/" + response + ".json"));
    }
}