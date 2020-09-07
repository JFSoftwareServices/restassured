package options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.json"},
        features = "classpath:features",
        glue = {"steps"}
)
public class CucumberTest {
}