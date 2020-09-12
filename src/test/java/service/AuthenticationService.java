package service;

import io.restassured.response.Response;
import model.UserLoginDetails;
import request.HttpMethod;

public class AuthenticationService {

    public String authenticate(String path, UserLoginDetails userLoginDetails) {
        Response response = new RestfulApiService()
                .setPath(path)
                .setBody(userLoginDetails)
                .setHttpMethod(HttpMethod.POST)
                .send();

        return response.getBody().jsonPath().get("access_token");
    }
}