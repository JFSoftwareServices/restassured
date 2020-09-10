package request;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public interface HttpRequest {
    ResponseOptions<Response> send(String uri, RequestSpecification requestSpecification);
}