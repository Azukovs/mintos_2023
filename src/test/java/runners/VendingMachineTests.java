package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"steps", "hooks"},
        features = "src/test/resources/features/vending",
        tags = "not @wip",
        plugin = {"pretty"}
)
public class VendingMachineTests {

}