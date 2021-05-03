package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class PortfolioManager {

    private Portfolio portfolio;

    public PortfolioManager(Portfolio portfolio){
        this.portfolio = portfolio;

    }





    public void buyStock(MarketSensor sensor, String symbol, int i) throws IOException {
        //Stock stock = YahooFinance.get(symbol);
        List<HistoricalQuote>history = sensor.getHistory(symbol);

        //System.out.println(history.get(i));



        BigDecimal pricing = history.get(i).getClose();


        //System.out.println(pricing);

        double currMoney = (portfolio.getBuyingPower()) * .10;

        double num = portfolio.getBuyingPower();

        if (currMoney > pricing.doubleValue()) {
            int shares = (int) (currMoney / pricing.doubleValue());



            if (portfolio.getPortfolio().containsKey(symbol)) {

                double sharesValue = portfolio.getPortfolio().get((symbol));
                double currValue = portfolio.getPriceBoughtAt().get(symbol);
                double currTotal = sharesValue*currValue;
                sharesValue += shares;
                double amount = pricing.doubleValue()*shares;
                double newTotal = ((currTotal+amount)/sharesValue);
                addAssets(symbol, shares, pricing, newTotal);
            } else {

                portfolio.getPortfolio().put((symbol), shares);
                portfolio.getPriceBoughtAt().put((symbol), (pricing.doubleValue()));
                portfolio.setBuyingPower(num - pricing.doubleValue()*shares);
                addAssets(symbol, shares, pricing, pricing.doubleValue());


            }

        }
    }

    public void addAssets(String symbol, int shares, BigDecimal pricing, double priceBoughtAt){
        if (portfolio.getPortfolio().containsKey(symbol)){
            portfolio.getPortfolio().put(symbol, portfolio.getPortfolio().get(symbol)+shares);
        }
        else{
            portfolio.getPortfolio().put(symbol, shares);
        }
        portfolio.getPriceBoughtAt().put((symbol), priceBoughtAt);
        portfolio.setBuyingPower(portfolio.getBuyingPower() - (pricing.doubleValue() * shares));        
    }

    public void sellStock(MarketSensor sensor, String symbol, int i) throws IOException {
//        Stock stock = YahooFinance.get(symbol);
//        BigDecimal currPrice = sensor.getStockPrice(symbol);

        List<HistoricalQuote>history = sensor.getHistory(symbol);

        BigDecimal currPrice = history.get(i).getClose();

        if(portfolio.getPortfolio().containsKey(symbol)){
            double valueBoughtAt = portfolio.getPriceBoughtAt().get(symbol);
            int shares = portfolio.getPortfolio().get(symbol);
            if(currPrice.doubleValue() > valueBoughtAt){
                portfolio.setBuyingPower((portfolio.getBuyingPower())+((currPrice.doubleValue()-valueBoughtAt)+valueBoughtAt)*shares);
                portfolio.getPortfolio().remove(symbol);
                portfolio.getPriceBoughtAt().remove(symbol);



            }

        }

    }

    public Portfolio getPortfolio(Portfolio portfolio) {

        return portfolio;

    }


    public double getAssets(Portfolio portfolio){


        double currBuyingPower = portfolio.getBuyingPower();

        for(String stock : portfolio.getPortfolio().keySet()){
            currBuyingPower += (portfolio.getPortfolio().get(stock)*portfolio.getPriceBoughtAt().get(stock));
        }


        return currBuyingPower;
        }



}
