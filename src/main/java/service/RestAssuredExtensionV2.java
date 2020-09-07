package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.Map;

/**
 * @Depricated This is not used
 */
public class RestAssuredExtensionV2 {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     * RestAssuredExtensionv2 constructor to pass the initial settings for the the following method
     *
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredExtensionV2(String uri, String method, String token) {

        //Formulate the API url
        this.url = "http://localhost:3000" + uri;
        this.method = method;

        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * ExecuteAPI to execute the API for GET/POST/DELETE
     *
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> ExecuteAPI() {
       /* RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(HttpMethod.POST.toString()))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(HttpMethod.DELETE.toString()))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(HttpMethod.GET.toString()))
            return request.get(this.url);*/
        return null;
    }

    public ResponseOptions<Response> Execute() {
        return ExecuteAPI();
    }

    /**
     * Authenticate to get the token variable
     *
     * @param body
     * @return string token
     */
    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * Executing API with query params being passed as the input of it
     *
     * @param queryPath
     * @return Reponse
     */
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String, String> queryPath) {
        builder.addQueryParams(queryPath);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithPathParams
     *
     * @param pathParams
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String, String> pathParams) {
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }

    /**
     * ExecuteWithPathParamsAndBody
     *
     * @param pathParams
     * @param body
     * @return
     */
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String, String> pathParams, Map<String, String> body) {
        builder.setBody(body);
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }


}
