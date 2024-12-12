package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html: target/reports/cucumber.html", "json: target/reports/cucumber.json"},
        glue = "steps",
        features = "C:\\Users\\iszaf\\Desktop\\Etrade\\src\\test\\resources\\features",
        tags = "@Sign-up",
        dryRun = true
)
public class CucumberRunner {
}

