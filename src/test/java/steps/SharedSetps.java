package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
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

    @Given("I authenticate using:")
    public void authenticate(DataTable dataTable) {
        String uri = dataTable.row(1).get(0);
        String email = dataTable.row(1).get(1);
        String password = dataTable.row(1).get(2);
        String token = new AuthenticationService().authenticate(uri, new UserLoginDetails(email, password));
        assertThat(token, is(notNullValue()));
        scenarioContext.put("token", token);
    }
}