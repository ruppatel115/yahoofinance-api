package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {



        RuleBasedAgent agent = new RuleBasedAgent();
        Simulator simulator = new Simulator(agent);

        simulator.yearSimulator();

        System.out.println(simulator.getPortfolio().getBuyingPower());




        //System.out.println(agent.chooseStock(simulator.getSensor()).getSymbol());



//        Calendar from = Calendar.getInstance();
//        Calendar to = Calendar.getInstance();
//        Interval daily = Interval.DAILY;
//        from.add(Calendar.YEAR, -1);
//

//







//        for(int i =0; i < historicalData.keySet().size(); i++){
//
//            //System.out.println(historicalData.values());
//
//            agent.buyStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());
//
//            agent.sellStock(simulator.getSensor(), agent.chooseStock(simulator.getSensor()).getSymbol());
//
//
//
//        }

        //simulator.getPortfolio().listPorfolio());





    }

}

