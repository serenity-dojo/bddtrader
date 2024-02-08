package net.bddtrader.unittests.tops;

import net.bddtrader.tops.TopController;
import net.bddtrader.tradingdata.TradingData;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.bddtrader.config.TradingDataSource.DEV;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenRequestingTheTops {

    TopController controller;

    @BeforeEach
    public void prepareNewsController() {
        controller = new TopController(DEV);
        TradingData.instanceFor(DEV).reset();
    }

    @Test
    public void shouldFindTheLatestPriceForAParticularStockFromStaticData() {
        assertThat(controller.lastTops()).isNotEmpty();
    }

}
