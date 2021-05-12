package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SensorInterface {

    String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO", "GOOG"};
    //Map<String, Stock> stocks = YahooFinance.get(symbols, true);
    
    /**
     * @return a map of stocks
     */
    public Map<String, Stock>getStocks();

    /**
     * @param ticker a stock ticker that references the stock who's closing price will be retrieved 
     * @throws IOException when there's a connection problem
     * @return the closing price of the desired stock in BigDecimal format
     */
    public BigDecimal getStockPrice(String ticker) throws IOException;


    /**
     * @param ticker a stock ticker that references the stock who's closing price will be retrieved 
     * @throws IOException when there's a connection problem
     * @return a list of historical quotes from this stock
     */
    public List<HistoricalQuote>getHistory(String ticker) throws IOException;










}
