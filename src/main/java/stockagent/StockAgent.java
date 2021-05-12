package stockagent;

import java.io.IOException;

import yahoofinance.Stock;

public interface StockAgent {

    /**
     * @param sensor a MarketSensor used to get stock history from YahooFinance
     * @throws IOException when there's a connection problem
     * @return a stock that is chosen by the agent to be bought or sold
     */
    public Stock chooseStock(MarketSensor sensor) throws IOException;
}
