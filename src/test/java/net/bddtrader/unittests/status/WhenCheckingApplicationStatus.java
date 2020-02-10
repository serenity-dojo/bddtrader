package net.bddtrader.unittests.status;

import net.bddtrader.config.TradingDataSource;
import net.bddtrader.status.StatusController;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenCheckingApplicationStatus {

    StatusController controller;

    @Before
    public void prepareNewsController() {
        controller = new StatusController(TradingDataSource.DEV);
    }

    @Test
    public void statusShouldIncludeTradeDataSource(TradingDataSource dataSource, String statusMessage) {

        controller = new StatusController(dataSource);

        assertThat(controller.status()).isEqualTo(statusMessage);
    }

}
