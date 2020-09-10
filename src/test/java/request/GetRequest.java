package request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static request.HttpRequest.request;

public class GetRequest implements HttpRequest {
    @Override
    public Response send(String uri, RequestSpecification requestSpecification) {
        return request(requestSpecification).get(uri);
    }
}