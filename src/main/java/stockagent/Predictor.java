package stockagent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class Predictor implements Forecaster {

    @Override
    public double predictRateOfChange(Stock stock, Calendar from) throws IOException {
        // TODO Auto-generated method stub
        List<HistoricalQuote> history = stock.getHistory(from);
        List <Double> closePriceHistory = new ArrayList<Double>();
        List <Double> avgRatesofChange = new ArrayList<Double>();
        double averageRateOfChange = 0;
        
        //adding history of close price of the stock to a list
        for (int i = 0; i < history.size(); i++) {
            closePriceHistory.add(history.get(i).getClose().doubleValue());
        }

        int k = 0;
        double prev = 0;
        double curr = 0;

        //adding difference in closing price between each day to list 
        while (k != closePriceHistory.size()){
            curr = closePriceHistory.get(k+1);
            prev = closePriceHistory.get(k);
            avgRatesofChange.add(curr - prev);
            k++;
        }

        //adding up all differences 
        for (int j = 0; j < avgRatesofChange.size(); j++) {
           averageRateOfChange += avgRatesofChange.get(j);
        }

        //averaging the differences 
        averageRateOfChange = averageRateOfChange/avgRatesofChange.size();
        
        return averageRateOfChange;
    }
    
}
