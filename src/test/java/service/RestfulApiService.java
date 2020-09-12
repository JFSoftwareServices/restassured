package service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
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

    public RestfulApiService setHeader(Header header) {
        requestSpecBuilder.addHeader(header.getName(), header.getValue());
        return this;
    }

    public RestfulApiService setBody(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public RestfulApiService setPathParams(Map<String, Integer> params) {
        requestSpecBuilder.addPathParams(params);
        return this;
    }

    public RestfulApiService setQueryParams(Map<String, Integer> queryParams) {
        requestSpecBuilder.addQueryParams(queryParams);
        return this;
    }

    public Response send() {
        requestSpecBuilder.setContentType(ContentType.JSON);
        HttpRequest httpRequest = httpMethod.createHttpRequest();
        return httpRequest.send(path, requestSpecBuilder.build());
    }
}