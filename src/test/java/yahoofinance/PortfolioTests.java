package yahoofinance;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.Assert.*;

import stockagent.*;
import yahoofinance.histquotes.HistoricalQuote;



public class PortfolioTests {
    
    @Test
    public void addAssetTests() throws IOException{
        RandomAgent agent = new RandomAgent();
        Simulator simulator = new Simulator(agent);
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);

        Portfolio testPortfolio = new Portfolio(100000);

        PortfolioManager testManager = new PortfolioManager(testPortfolio, simulator.getSensor());

        //empty portfolio
        assertEquals(true, testPortfolio.getPortfolio().isEmpty());

        //buying power
        assertEquals(100000, testPortfolio.getBuyingPower(), 0.001);

        //stock to add
        Stock baba = stockList.get(1);
        assertEquals("BABA", baba.getSymbol());

        //getting price
        double currPrice = baba.getQuote().getPrice().doubleValue();

        //add asset
        testManager.addAssets(baba.getSymbol(), 3, currPrice);

        //checking portfolio
        assertEquals(1, testPortfolio.getPortfolio().size());
        assertEquals("BABA", testPortfolio.getPortfolio().keySet().iterator().next());
        assertEquals(3, testPortfolio.getPortfolio().entrySet().iterator().next().getValue().intValue());

        //buying power
        assertEquals(100000-currPrice*3, testPortfolio.getBuyingPower(), 0.001);
        

        //adding a different stock
        Stock tesla = stockList.get(2);
        currPrice = tesla.getQuote().getPrice().doubleValue();

        //adding asset
        double oldBP = testPortfolio.getBuyingPower();
        testManager.addAssets(tesla.getSymbol(), 1, currPrice);

        //checking portfolio
        Iterator<Entry<String,Integer>> iterator = testPortfolio.getPortfolio().entrySet().iterator();
        iterator.next();
        assertEquals(2, testPortfolio.getPortfolio().size());
        assertEquals(1, iterator.next().getValue().intValue());

        //buying power
        assertEquals(oldBP-currPrice, testPortfolio.getBuyingPower(), 0.001);

    }


    @Test
    public void sellTests() throws IOException{

        RandomAgent agent = new RandomAgent();
        Simulator simulator = new Simulator(agent);
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);

        Portfolio testPortfolio = new Portfolio(100000);

        PortfolioManager testManager = new PortfolioManager(testPortfolio, simulator.getSensor());

        //empty portfolio
        assertEquals(true, testPortfolio.getPortfolio().isEmpty());

        //buying power
        assertEquals(100000, testPortfolio.getBuyingPower(), 0.001);
        double oldBP = testPortfolio.getBuyingPower();

        //buying stock
        Stock baba = stockList.get(1);
        double babaPrice = baba.getQuote().getPrice().doubleValue();
        assertEquals("BABA", baba.getSymbol());
        Stock tesla = stockList.get(2);
        assertEquals("TSLA", tesla.getSymbol());
        double teslaPrice = tesla.getQuote().getPrice().doubleValue();
        testManager.addAssets("BABA", 3, babaPrice);
        testManager.addAssets("TSLA", 3, teslaPrice);

        //checking buying power
        assertEquals(oldBP - (babaPrice*3) - (teslaPrice*3), testPortfolio.getBuyingPower(), 0.001);
        oldBP = testPortfolio.getBuyingPower();

        //selling BABA
        double currPrice = simulator.getSensor().getHistory("BABA").get(0).getClose().doubleValue();
        testManager.sellStock(simulator.getSensor(), "BABA", 0);

        //checking buying power
        assertEquals(oldBP + (currPrice*3), testPortfolio.getBuyingPower(), 0.001);
        oldBP = testPortfolio.getBuyingPower();

        //checking portfolio
        assertEquals(false, testPortfolio.getPortfolio().containsKey("BABA"));

        //selling TSLA
        currPrice = simulator.getSensor().getHistory("TSLA").get(0).getClose().doubleValue();
        testManager.sellStock(simulator.getSensor(), "TSLA", 0);

        //checking buying power
        assertEquals(oldBP + (currPrice*3), testPortfolio.getBuyingPower(), 0.001);
    

        //checking portfolio
        assertEquals(true, testPortfolio.getPortfolio().isEmpty());



    }




}