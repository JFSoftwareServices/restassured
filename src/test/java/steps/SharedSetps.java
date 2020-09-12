package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import model.UserLoginDetails;
import request.HttpMethod;
import service.RestfulApiService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SharedSetps {
    private ScenarioContext scenarioContext;

    public SharedSetps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I authenticate using:")
    public void authenticate(DataTable dataTable) {
        String path = dataTable.cell(1, 0);
        String email = dataTable.cell(1, 1);
        String password = dataTable.cell(1, 2);

        String token = authenticate(path, new UserLoginDetails(email, password));
        assertThat(token, is(notNullValue()));
        scenarioContext.put(Constants.TOKEN, token);
    }

    private String authenticate(String path, UserLoginDetails userLoginDetails) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
                .setBasePath(path)
                .setBody(userLoginDetails).build();

        Response response = RestfulApiService.send(builder, HttpMethod.POST);
        return response.getBody().jsonPath().get("access_token");
    }
}