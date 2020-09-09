package request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class PostRequest implements HttpRequest {
    @Override
    public ResponseOptions<Response> send(String uri, RequestSpecification requestSpecification) {
        return RestAssured.given()
                .spec(requestSpecification)
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(uri);
    }
}
