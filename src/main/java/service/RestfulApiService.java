package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import model.UserLoginDetails;
import request.HttpMethod;
import request.HttpRequest;

public class RestfulApiService {
    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private RequestSpecification requestSpecification = requestSpecBuilder.build();
    private HttpMethod httpMethod;
    private String basePath;

    public ResponseOptions<Response> send() {
        requestSpecification.contentType(ContentType.JSON);
        HttpRequest httpRequest = httpMethod.createHttpRequest();
        String uri = "http://localhost:3000" + basePath;
        return httpRequest.send(uri, requestSpecification);
    }

    public RestfulApiService setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public RestfulApiService setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RestfulApiService setUserDetails(String email, String password) {
        requestSpecification.body(new UserLoginDetails(email, password));
        return this;
    }

    public RestfulApiService setHeader(Header header) {
        requestSpecification.header(header);
        return this;
    }
}