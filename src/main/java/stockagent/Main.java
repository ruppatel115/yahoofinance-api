package stockagent;


import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {


        StockAgent agent = new RandomAgent();

        Simulator simulator = new Simulator(agent);


        Portfolio portfolio = new Portfolio(100000);
        PortfolioManager manager = new PortfolioManager(portfolio, simulator.getSensor());
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();


        List<Stock> stockList = simulator.getStockInfo(simulator.getSensor().getSymbols());

        Map<Stock, List<HistoricalQuote>> historicalData = simulator.getHistoricalData(stockList);

        simulator.setFrom(from);


        Calendar start = Calendar.getInstance();
        start.setTime(from.getTime());
        Calendar end = Calendar.getInstance();
        end.setTime(to.getTime());

        manager.buyStock(simulator.getSensor(), "DASH", 0);
        manager.buyStock(simulator.getSensor(), "ABT", 0);
        manager.buyStock(simulator.getSensor(), "ABBV", 0);


        int i = 0;
        for(Stock stock : historicalData.keySet()){
            //int size = historicalData.get(stock).size();
            
            while(i < 100){

                stock = agent.chooseStock(simulator.getSensor());
                manager.buyStock(simulator.getSensor(), stock.getSymbol(), i);
                stock = agent.chooseStock(simulator.getSensor());


                if(portfolio.getPortfolio().size() > 0) {
                    manager.sellStock(simulator.getSensor(), stock.getSymbol(), i);
                }

                System.out.println("\n");
                System.out.println("BuyingPower: ");
                System.out.println(portfolio.getBuyingPower() + "\n");
                System.out.println("Total Asset Value: ");
                System.out.println(manager.getAssets(portfolio, simulator.getSensor(), i) + "\n");
                System.out.println("Stocks/shares owned: ");
                System.out.println(portfolio.getPortfolio() + "\n");
                System.out.println("Stock Price Bought At: ");
                System.out.println(portfolio.getPriceBoughtAt() + "\n");

                i+=1;
            }

        }

        System.out.println("DONE");

    }
}















