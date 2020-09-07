package hooks;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;

public class Hooks {
    @Before
    public void initialization() {
        LogConfig logConfig = logConfig()
                .enablePrettyPrinting(true)
                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

        RestAssured.config().logConfig(logConfig);

        JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4)
                                .freeze())
                .freeze();

        JsonSchemaValidator.settings = settings()
                .with().jsonSchemaFactory(factory)
                .and().with().checkedValidation(false);
    }
}