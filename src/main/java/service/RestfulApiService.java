package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import request.HttpMethod;
import request.HttpRequest;

import java.util.Map;

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

    public RestfulApiService setPathParams(Map<String, String> parameterNameValuePairs) {
        requestSpecification.pathParams(parameterNameValuePairs);
        return this;
    }

    public RestfulApiService setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }
}