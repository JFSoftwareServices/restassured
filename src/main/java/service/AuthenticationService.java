package service;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import request.HttpMethod;

public class AuthenticationService {

    public String authenticate(String path, String email, String password) {
        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setUserDetails(email, password)
                .setHttpMethod(HttpMethod.POST)
                .send();

       return responseOptions.getBody().jsonPath().get("access_token");
    }
}