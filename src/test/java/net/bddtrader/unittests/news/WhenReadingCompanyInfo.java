package net.bddtrader.unittests.news;

import net.bddtrader.stocks.Company;
import net.bddtrader.stocks.StockController;
import net.bddtrader.tradingdata.services.NoSuchCompanyException;
import net.bddtrader.tradingdata.services.UnknownCompanyException;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(SerenityJUnit5Extension.class)
public class WhenReadingCompanyInfo {

    StockController controller;

    @BeforeEach
    public void prepareController() {
        controller = new StockController(DEV);
    }

    @Test
    public void shouldFindTheLatestNewsAboutAParticularStockFromStaticData() {

        Company apple = controller.companyDetailsFor("AAPL");

        assertThat(apple.getCompanyName()).isEqualTo("Apple, Inc.");
    }

    @Test
    public void stockCodesCanBeUpperOrLowerCase() {

        Company apple = controller.companyDetailsFor("aapl");

        assertThat(apple.getCompanyName()).isEqualTo("Apple, Inc.");
    }

    @Test
    public void unknownCompanyShouldResultInAMeaningfulException() {
        assertThrows(NoSuchCompanyException.class,()-> controller.companyDetailsFor("unknown"));;
    }

}
