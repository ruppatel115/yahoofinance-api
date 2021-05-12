package stockagent;

import java.io.IOException;
import java.util.Calendar;

import yahoofinance.Stock;

public interface Forecaster {

    public double predictRateOfChange(Stock stock, Calendar from) throws IOException; 
    
}
