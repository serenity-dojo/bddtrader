package net.bddtrader.apitests.news;

import net.bddtrader.news.NewsController;
import net.bddtrader.news.NewsItem;
import net.bddtrader.stocks.Company;
import net.bddtrader.stocks.StockController;
import net.bddtrader.tradingdata.services.UnknownCompanyException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;

public class WhenReadingCompanyInfo {

    StockController controller;

    @Before
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

    @Test(expected = UnknownCompanyException.class)
    public void unknownCompanyShouldResultInAMeaningfulException() {
        controller.companyDetailsFor("unknown");
    }

}
