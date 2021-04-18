package runner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = "stepdefinitions"
)
public class RunnerTests { }
