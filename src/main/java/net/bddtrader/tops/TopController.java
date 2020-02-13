package net.bddtrader.tops;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bddtrader.config.TraderConfiguration;
import net.bddtrader.config.TradingDataSource;
import net.bddtrader.stocks.Company;
import net.bddtrader.tradingdata.TradingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api("top")
public class TopController {

    private final TradingDataSource tradingDataSource;

    public TopController(TradingDataSource tradingDataSource) {
        this.tradingDataSource = tradingDataSource;
    }

    @Autowired
    public TopController(TraderConfiguration traderConfiguration) {
        this(traderConfiguration.getTradingDataSource());
    }

    @RequestMapping(value="/api/tops/last", method = GET)
    @ApiOperation("Find the latest trades, in summary form")
    public List<Top> lastTops() {
        return TradingData.instanceFor(tradingDataSource).getTops();
    }
}
