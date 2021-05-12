package stockagent;

import java.io.IOException;
import java.util.Calendar;

import yahoofinance.Stock;

public interface Forecaster {

    /**
     * @param stock a stock to get the history of and predict the future trend
     * @param from the range of days of history collected for that stock 
     * @throws IOException when there's a connection problem
     * @return a double that is the average rate of change from the date you started 
     * collecting the history to today
     */
    public double predictRateOfChange(Stock stock, Calendar from) throws IOException; 
    
}
