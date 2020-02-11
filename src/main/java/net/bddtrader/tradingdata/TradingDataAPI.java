package net.bddtrader.tradingdata;

import java.util.List;

public interface TradingDataAPI extends PriceReader, CompanyReader, PriceUpdater, NewsReader {
    void reset();

    List<Trade> getLatestTrades();
}
