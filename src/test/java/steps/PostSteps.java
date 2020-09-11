package steps;

import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import model.Post;
import request.HttpMethod;
import service.RestfulApiService;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostSteps {
    private ScenarioContext scenarioContext;

    public PostSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("I request for a post using {string}")
    public void requestForPost(String path) {
        request(path);
    }

    @When("I request for posts using {string}")
    public void requestForPosts(String path) {
        request(path);
    }

    private void request(String path) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setPath(path)
                .setHttpMethod(HttpMethod.GET)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();
        scenarioContext.put("response", response);
    }

    @Then("the response from the request for a post should be in the correct format")
    public void assertInCorrectFormat() {
        Response response = scenarioContext.get("response", Response.class);
        String body = response.getBody().asString();
        assertThat(body, matchesJsonSchemaInClasspath("schemas/post.json"));
    }

    @When("I create a post using {string}, {string}, {string}, {int}")
    public void createPost(String path, String title, String author, Integer id) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setPath(path)
                .setHttpMethod(HttpMethod.POST)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .setBody(Post.builder().title(title).author(author).id(id).build())
                .send();
        scenarioContext.put("response", response);
    }

    @When("I request for a post with {string} and id {int}")
    public void requestForPost(String path, Integer id) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setPath(path + "/" + id)
                .setHttpMethod(HttpMethod.GET)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();
        scenarioContext.put("response", response);
    }

    @When("I delete a post using {string} with id {int}")
    public void deletePost(String path, Integer id) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setPath(path + "/" + id)
                .setHttpMethod(HttpMethod.DELETE)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();
        scenarioContext.put("response", response);
    }

    @When("I update post title to {string} using {string} and id {int}")
    public void updatePostTitle(String title, String path, Integer id) {
        String token = scenarioContext.get("token", String.class);
        Response response = new RestfulApiService()
                .setPath(path + "/" + id)
                .setHttpMethod(HttpMethod.PUT)
                .setBody(Post.builder().title(title).build())
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();
        scenarioContext.put("response", response);
    }

    @Then("The title of the post should be {string}")
    public void assertPostTitle(String title) {
        Post post = scenarioContext.get("response", Response.class).getBody().as(Post.class);
        assertThat(post.getTitle(), equalTo(title));
    }

    @Then("I should not see a post")
    public void assertNoPostPresent() {
        Response response = scenarioContext.get("response", Response.class);
        assertThat(response.getBody().print(), equalTo("{}"));
    }

    @Then("I should see first two author name as {string}")
    public void assertAuthorNames(String name) {
        Type type = new TypeToken<List<Post>>() {
        }.getType();
        List<Post> posts = scenarioContext.get("response", Response.class)
                .getBody().as(type, ObjectMapperType.GSON);
        assertThat(posts.get(0).getAuthor(), equalTo(name));
        assertThat(posts.get(1).getAuthor(), equalTo(name));
    }

    @Then("I should see author name as {string}")
    public void assertAuthorName(String name) {
        Post post = scenarioContext.get("response", Response.class).getBody().as(Post.class);
        assertThat(post.getAuthor(), equalTo(name));
    }
}