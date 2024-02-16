package net.bddtrader.unittests.status;

import net.bddtrader.config.TradingDataSource;
import net.bddtrader.status.StatusController;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCheckingApplicationStatus {

    StatusController controller;

    @BeforeEach
    public void prepareNewsController() {
        controller = new StatusController(TradingDataSource.DEV);
    }

    @Test
    public void statusShouldIncludeTradeDataSource() {

        controller = new StatusController(TradingDataSource.DEV);

        assertThat(controller.status()).isEqualTo("BDDTrader running against DEV");
    }

}
