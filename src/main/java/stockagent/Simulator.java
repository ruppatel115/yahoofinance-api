package stockagent;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.*;

public class Simulator {



    private List<Stock> stockList = new ArrayList<Stock>();

    private Portfolio portfolio = new Portfolio(1000000);
    private MarketSensor sensor = new MarketSensor();
    private Calendar from = Calendar.getInstance();
    private Calendar to = Calendar.getInstance();
    private Interval daily = Interval.DAILY;
    private RuleBasedAgent agent;




    public Simulator(StockAgent stockAgent) throws IOException {
        from.add(Calendar.YEAR, -1);

    }

    public List<Stock> getStockInfo(String [] symbols) throws IOException {
        for(int i =0; i < symbols.length; i++){

            stockList.add(YahooFinance.get(symbols[i]));
        }

        return stockList;

    }


    public Map<Stock,List<HistoricalQuote>> getHistoricalData(List<Stock>stockList) throws IOException {

        Map<Stock, List<HistoricalQuote>>data = new HashMap<Stock, List<HistoricalQuote>>();

        for(int i =0; i < stockList.size(); i++){

            data.put(stockList.get(i), sensor.getHistory(stockList.get(i).getSymbol()));

        }

        return data;

    }


    public Portfolio getPortfolio() {
        return portfolio;
    }


    public MarketSensor getSensor() {
        return sensor;
    }


    public double priceOnDay(Stock stock, List<HistoricalQuote>list) throws IOException {



        return sensor.getStockPrice(stock.getSymbol()).doubleValue();



    }



    public void yearSimulator() throws IOException {
        Calendar start = Calendar.getInstance();
        start.setTime(from.getTime());
        Calendar end = Calendar.getInstance();
        end.setTime(to.getTime());

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {





                //System.out.println(portfolio.getBuyingPower());
//
//
//
//
//                double price = sensor.getStockPrice(stockList.get(i).getSymbol()).doubleValue();
//
//
//                agent.buyStock(sensor, stockList.get(i).getSymbol());
//
//
//                System.out.println(price);

//                System.out.println(price);


            }
            // Do your job here with `date`.
        }

        // System.out.println(portfolio);

    }



