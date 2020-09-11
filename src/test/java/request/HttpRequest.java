package request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface HttpRequest {
    static RequestSpecification request(RequestSpecification requestSpecification) {
        return RestAssured.given()
                .spec(requestSpecification)
                .log().all()
                .contentType(ContentType.JSON);
    }

    Response send(String path, RequestSpecification requestSpecification);
}