package service;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.UserLoginDetails;
import request.HttpMethod;

public class AuthenticationService {

    public String authenticate(String path, UserLoginDetails userLoginDetails) {
        ResponseOptions<Response> responseOptions = new RestfulApiService()
                .setBasePath(path)
                .setUserDetails(userLoginDetails)
                .setHttpMethod(HttpMethod.POST)
                .send();

        return responseOptions.getBody().jsonPath().get("access_token");
    }
}