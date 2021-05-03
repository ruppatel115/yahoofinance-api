package yahoofinance;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert.*;

import stockagent.Portfolio;
import stockagent.PortfolioManager;
import stockagent.Simulator;
import yahoofinance.histquotes.HistoricalQuote;


public class PortfolioTests {
    
    @Test
    public void buyTests() throws IOException{
        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);

        Portfolio testPortfolio = new Portfolio(10000);

        PortfolioManager testManager = new PortfolioManager(testPortfolio);



    }






}