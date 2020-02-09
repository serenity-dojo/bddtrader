package net.bddtrader.tradingdata;

public interface TradingDataAPI extends PriceReader, CompanyReader, PriceUpdater, NewsReader {
    void reset();
}
