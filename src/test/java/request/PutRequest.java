package request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static request.HttpRequest.request;

public class PutRequest implements HttpRequest {
    @Override
    public Response send(String path, RequestSpecification requestSpecification) {
        return request(requestSpecification).put(path);
    }
}