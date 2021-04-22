package yahoofinance;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert.*;
import stockagent.RuleBasedAgent;
import stockagent.Simulator;
import yahoofinance.histquotes.HistoricalQuote;

public class AgentTests {
    
    @Test
    public void buyTests() throws IOException{
        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);
        Map<Stock, List<HistoricalQuote>>historicalData = new HashMap<Stock, List<HistoricalQuote>>();
        RuleBasedAgent agent = new RuleBasedAgent(simulator.getPortfolio(), simulator.getSensor());

        //checking portfolio
        assertEquals(1000000, simulator.getPortfolio().getBuyingPower(), .001);

        //gets current price of stock
        double babaPrice = stockList.get(1).getQuote().getPrice().doubleValue();

        //how much money can be spent on one stock
        double currMoney = simulator.getPortfolio().getBuyingPower()*.10;

        //how many shares should be purchased
        int shares = (int) (currMoney/babaPrice);

        //stock object
        Stock baba = YahooFinance.get("BABA");

        //buying stock
        agent.buyStock(simulator.getSensor(), "BABA");        

        //checks that a stock was purchased
        assertEquals(1, simulator.getPortfolio().getPortfolio().size());

        //checks that it was purchased at the current price
        assertEquals(babaPrice, simulator.getPortfolio().getPriceBoughtAt().get(baba), .001);

        //checks that the right amount was purchased
        assertEquals(shares, (int) simulator.getPortfolio().getPortfolio().get(baba));

        



    }

    


    
}
