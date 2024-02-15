package net.bddtrader.unittests.stocks;

import net.bddtrader.clients.Client;
import net.bddtrader.exceptions.MissingMandatoryFieldsException;
import net.bddtrader.stocks.StockController;
import net.bddtrader.tradingdata.TradingData;
import net.bddtrader.tradingdata.services.NoSuchCompanyException;
import net.bddtrader.tradingdata.services.UnknownCompanyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WhenRequestingTheLatestTrades {

    StockController controller;

    @BeforeEach
    public void prepareController() {
        controller = new StockController(DEV);
        TradingData.instanceFor(DEV).reset();
    }

    @Test
    public void shouldFindTheLatestPriceForAParticularStockFromStaticData() {
        assertThat(controller.bookDetailsFor ("AAPL").getQuote().getSymbol()).isEqualTo("AAPL");
        assertThat(controller.bookDetailsFor ("AAPL").getTrades()).isNotEmpty();
    }

    @Test
    public void shouldThrowExceptionIfNoCompanyFound() {
        assertThrows(NoSuchCompanyException.class,
        ()-> controller.bookDetailsFor ("Unknown").getQuote().getSymbol());
    }

}
