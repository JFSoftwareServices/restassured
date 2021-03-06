package request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PutRequest implements HttpRequest {
    @Override
    public Response send(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).put();
    }
}