package net.bddtrader.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bddtrader.config.TraderConfiguration;
import net.bddtrader.config.TradingDataSource;
import net.bddtrader.tradingdata.TradingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Tag(name = "news")
public class NewsController {

    private final TradingDataSource tradingDataSource;

    public NewsController(TradingDataSource tradingDataSource) {
        this.tradingDataSource = tradingDataSource;
    }
    @Autowired
    public NewsController(TraderConfiguration traderConfiguration) {
        this(traderConfiguration.getTradingDataSource());
    }

    @RequestMapping(value="/api/news", method = GET)
    @Operation(summary = "Get news articles about a given stock",
                  description = "Use 'market' to get market-wide news.")
    public List<NewsItem> newsFor(@RequestParam(required = false) String symbols) {
        List<String> requestedStockids;

        if (symbols == null || symbols.isEmpty()) {
            requestedStockids = new ArrayList<>();
        } else {
            requestedStockids = Arrays.stream(symbols.split(","))
                    .map(stock -> stock.trim().toUpperCase())
                    .collect(Collectors.toList());
        }
        return TradingData.instanceFor(tradingDataSource).getNewsFor(requestedStockids);
    }
}
