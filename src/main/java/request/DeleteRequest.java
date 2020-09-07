package request;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest implements HttpRequest {
    @Override
    public ResponseOptions<Response> send(String uri, RequestSpecification requestSpecification) {
        return null;
    }
}