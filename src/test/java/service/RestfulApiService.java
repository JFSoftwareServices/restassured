package service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.UserLoginDetails;
import request.HttpMethod;
import request.HttpRequest;

import java.util.Map;

public class RestfulApiService {
    private static final String BASE_URI = "http://localhost";
    private static final int PORT = 3000;

    static {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }

    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private RequestSpecification requestSpecification = requestSpecBuilder.build();
    private HttpMethod httpMethod;
    private String path;

    public RestfulApiService setPath(String path) {
        this.path = path;
        return this;
    }

    public RestfulApiService setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public RestfulApiService setUserDetails(UserLoginDetails userDetails) {
        requestSpecification.body(userDetails);
        return this;
    }

    public RestfulApiService setHeader(Header header) {
        requestSpecification.header(header);
        return this;
    }

    public RestfulApiService setBody(Object body) {
        requestSpecification.body(body);
        return this;
    }

    public Response send() {
        requestSpecification.contentType(ContentType.JSON);
        HttpRequest httpRequest = httpMethod.createHttpRequest();
        return httpRequest.send(path, requestSpecification);
    }

    public RestfulApiService setPathParams(Map<String, Integer> params) {
        requestSpecification.pathParams(params);
        return this;
    }

    public RestfulApiService setQueryParams(Map<String, Integer> queryParams) {
        requestSpecification.queryParams(queryParams);
        return this;
    }
}