package schema;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.not;

public class SinglePostSchemaTests {
    private static final String SINGLE_POST = "{\n" +
            "\"id\": 1,\n" +
            "\"title\": \"TitleValue\",\n" +
            "\"author\": \"AuthorValue\"\n" +
            "}";
    private static WireMockServer server = new WireMockServer();
    private static JsonSchemaFactory jsonSchemaFactory;
    private static ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();

    @BeforeClass
    public static void setUp() {
        jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();

        server.start();
    }

    @AfterClass
    public static void teardown() {
        server.stop();
    }

    @Test
    public void testSchemaValidationForIncorrectIdValue() {
        mockResponse.withStatus(200).withBody(SINGLE_POST.replace("\"TitleValue\"", "5"));
        arrangeActAssert();
    }

    @Test
    public void testSchemaValidationForIncorrectIdKey() {
        mockResponse.withStatus(200).withBody(SINGLE_POST.replace("id", "idX"));
        arrangeActAssert();
    }

    private void arrangeActAssert() {
        WireMock.stubFor(WireMock.get("/posts/1")
                .willReturn(mockResponse)
        );
        RestAssured.given()
                .when()
                .get("/posts/1")
                .then().assertThat()
                .body(not(matchesJsonSchemaInClasspath("schemas/post.json")
                        .using(jsonSchemaFactory)));
    }
}