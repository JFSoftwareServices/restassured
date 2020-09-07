package hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;

import static io.restassured.config.LogConfig.logConfig;

public class Hooks {

    @Before
    public void initialization() {

        LogConfig logConfig = logConfig()
                .enablePrettyPrinting(true)
                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

        RestAssured.config().logConfig(logConfig);
    }
}