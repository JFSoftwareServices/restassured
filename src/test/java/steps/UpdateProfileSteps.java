package steps;

public class UpdateProfileSteps {

   /* public static ResponseOptions<Response> responseOptions;

    @Given("^I Perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) {

        var data = table.raw();
        HashMap<String, String> body = new HashMap<>();
        body.put("name", data.get(1).get(0));

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", data.get(1).get(1));

        responseOptions = RestAssuredExtensionV2.ExecuteWithPathParamsAndBody(url, pathParams, body);
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(responseOptions.getBody().jsonPath().get("name"), equalTo(name));
    }


    @Given("^I ensure to Perform POST operation for \"([^\"]*)\" with body as$")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable table)  {
        var data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I Perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable table)  {
        var data = table.raw();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));
        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition, String title) {
        if (condition.equalsIgnoreCase("should not"))
            assertThat(responseOptions.getBody().jsonPath().get("title"), IsNot.not(title));
        else
            assertThat(responseOptions.getBody().jsonPath().get("title"), is(title));
    }

    @And("^I perform GET operation with path parameter for \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) {
        var data = table.raw();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));
        responseOptions = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @And("^I Perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table)  {
        var data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url, body, pathParams);
    }*/
}