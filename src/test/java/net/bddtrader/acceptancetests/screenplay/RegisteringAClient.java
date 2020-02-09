package net.bddtrader.acceptancetests.screenplay;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/clients/registering_a_new_client.feature"
)
public class RegisteringAClient {
}
