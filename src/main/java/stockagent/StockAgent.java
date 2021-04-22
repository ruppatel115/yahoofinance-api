package stockagent;

import yahoofinance.Stock;

import java.io.IOException;

public interface StockAgent {



    public void buyStock(MarketSensor sensor, String symbol) throws IOException;

    public void sellStock(MarketSensor sensor, String symbol) throws IOException;


    public Stock chooseStock(MarketSensor sensor);


}
