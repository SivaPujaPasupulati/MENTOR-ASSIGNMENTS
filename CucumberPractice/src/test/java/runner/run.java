package runner;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/cuc.feature",
        glue = {"step"},
        tags = "@Test"
)

public class run extends AbstractTestNGCucumberTests{

}
