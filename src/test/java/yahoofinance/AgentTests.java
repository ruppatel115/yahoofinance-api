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
    public static void buyTests() throws IOException{
        Simulator simulator = new Simulator();
        String[] symbols = new String[] {"INTC", "BABA", "TSLA", "GOOG"};
        List<Stock>stockList = simulator.getStockInfo(symbols);
        Map<Stock, List<HistoricalQuote>>historicalData = new HashMap<Stock, List<HistoricalQuote>>();
        RuleBasedAgent agent = new RuleBasedAgent(simulator.getPortfolio(), simulator.getSensor());

        //checking portfolio
        assertEquals(1000000, simulator.getPortfolio().getBuyingPower(), .001);

        

    }

    


    
}
