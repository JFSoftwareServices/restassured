package steps;

import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import request.HttpMethod;
import service.RestfulApiService;

public class DeleteProfileSteps {

    private ScenarioContext scenarioContext;

    public DeleteProfileSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @When("I delete to {string} with id {int}")
    public void deletePostWithId(String path, Integer id) {

        String token = scenarioContext.get("token", String.class);

        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path + "/" + id)
                .setHttpMethod(HttpMethod.DELETE)
                .setHeader(new Header("Authorization", "Bearer " + token))
                .send();

        scenarioContext.put("responseOptions", responseOptions);
    }
}