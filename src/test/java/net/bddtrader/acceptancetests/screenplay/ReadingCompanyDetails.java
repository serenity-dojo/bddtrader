package net.bddtrader.acceptancetests.screenplay;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/viewing_market_data/reading_company_details.feature"
)
public class ReadingCompanyDetails {
}
