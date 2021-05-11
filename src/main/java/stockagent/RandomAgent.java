package stockagent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import yahoofinance.Stock;

public class RandomAgent implements StockAgent{
    
    private Portfolio portfolio;
    private MarketSensor sensor;
    Random random = new Random();

    public RandomAgent(){
    }

    public Stock chooseStock(MarketSensor sensor){
        List<String> key = new ArrayList<String>(sensor.getStocks().keySet());

        String randomKey = key.get(random.nextInt(key.size()));



        return sensor.getStocks().get(randomKey);

    }
}
