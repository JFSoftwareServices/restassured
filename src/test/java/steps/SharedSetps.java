package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.UserLoginDetails;
import service.AuthenticationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SharedSetps {
    private ScenarioContext scenarioContext;

    public SharedSetps(ScenarioContext scenarioContext) {
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
}