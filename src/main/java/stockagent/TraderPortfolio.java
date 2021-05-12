package stockagent;

import yahoofinance.Stock;

import java.io.IOException;
import java.util.HashMap;

public interface TraderPortfolio {

    /**
     * 
     * @return a double representing the current buying power reflected in a portfolio
     */
    public double getBuyingPower();

    /**
     * 
     * @return a HashMap containing stock tickers (key) and how many shares owned (value)
     */
    public HashMap<String, Integer> getPortfolio();

}
