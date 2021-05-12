# Agent Definition
https://docs.google.com/document/d/1TroFGd6kVxd9nBFP8ytaitdginuaTjSVi07jxi7q4ec/edit

Our agent is simple reflex agent that has the ability to buy and sell stocks based on historical data extracted from our yahoo finance api enviornment. Our agent looks at a fixed number of stocks derived from the environment by our market sensor, and decides whether to sell or buy based ony the last 100 days of closing prices per stock. If the price is higher than what our agent bought it at it will sell otherwise if the price is lower or equal to the price at which our agent bought the stock at it will continue to buy that stock, spending only 10% of it's total buying power so that it does not exceed it's total buying power on that stock. We created a random agent that buys random stocks with no limit on how much it can spend to compare the two and almost all of the time our simple reflex agent performs better than the random agent.

# Quick Start Guide
To run our agent al you need to do is call up Main.java nad run that file. 

# How To Guide 
In order o make an agent that interacts with the enviornment, you must implement our StockAgent interface with your agent allowing yours to choose stocks that it might want to buy or sell. Then you'll need to implement a Portfolio and ProtfolioManager so that your agent has the ability to do the necessary calculations in order to decide which stocks to buy and which ones to sell. 

```
You can find our sensors in: src/main/java/stockagent

Actuator interface:
TraderPortfolio.java

Sensor interface:
SensorInterface.java

You can find our tests in: src/main/java/test/java/yahoofinance

Tests:
PortfolioTests.java
```
# Advanced Machine Learning Algorithm write-up
https://docs.google.com/document/d/1v_WELneFsAaMqnWmTj0XZd2SoyfGHc4tmovEv1vouNY/edit

----------------------------------------------------------------------------------------------------------------------------------
# Finance Quotes API for Yahoo Finance (Java)

[![Build Status](https://travis-ci.org/sstrickx/yahoofinance-api.svg?branch=master)](https://travis-ci.org/sstrickx/yahoofinance-api)

[Website](http://financequotes-api.com)

This library provides some methods that should make it easy to communicate with the Yahoo Finance API. It allows you to request detailed information, some statistics and historical quotes on stocks. Separate functionality is available to request a simple FX quote.
Please check the javadoc (available in dist directory) to get a complete overview of the available methods and to get an idea of which data is available from Yahoo Finance.

> This project is not associated with nor sponsored by Yahoo! Inc. Yahoo! Inc. is the exclusive owner of all trademark and other intellectual property rights in and to the YAHOO! and Y! trademarks (the "Trademarks"), including the stylized YAHOO! and Y! logos. Yahoo! Inc. owns trademark registrations for the Trademarks.

## Add to your project as a dependency
### Maven
```xml
<dependency>
    <groupId>com.yahoofinance-api</groupId>
    <artifactId>YahooFinanceAPI</artifactId>
    <version>x.y.z</version>
</dependency>
```
### Gradle
```groovy
dependencies {
    compile group: 'com.yahoofinance-api', name: 'YahooFinanceAPI', version: 'x.y.z'
}
```
### Ivy
```xml
<dependency org="com.yahoofinance-api" name="YahooFinanceAPI" rev="x.y.z" />
```

# Examples
## Single stock
```java
Stock stock = YahooFinance.get("INTC");

BigDecimal price = stock.getQuote().getPrice();
BigDecimal change = stock.getQuote().getChangeInPercent();
BigDecimal peg = stock.getStats().getPeg();
BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

stock.print();
```
Output:
```
INTC
--------------------------------
symbol: INTC
name: Intel Corporation
currency: USD
stockExchange: NasdaqNM
quote: Ask: 32.25, Bid: 32.24, Price: 32.2485, Prev close: 33.62
stats: EPS: 2.019, PE: 16.65, PEG: 1.74
dividend: Pay date: Mon Dec 01 06:00:00 CET 2014, Ex date: Tue Aug 05 06:00:00 CEST 2014, Annual yield: 2.68%
history: null
--------------------------------
```

## Single stock, easy refresh
```java
Stock stock = YahooFinance.get("INTC");
double price = stock.getQuote(true).getPrice();
```
This will also automatically refresh the statistics and dividend data of the stock in a single request to Yahoo Finance.
Please be aware that it wouldn't be a good idea to call the getQuote(true), getStats(true) or getDividend(true) too much in a short timespan as this will cost too much delay without providing any added value. There's no problem to call the versions of those methods without argument or with the argument set to false.

## Multiple stocks at once
```java
String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
Stock intel = stocks.get("INTC");
Stock airbus = stocks.get("AIR.PA");
```

## FX quote
```java
FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
FxQuote usdgbp = YahooFinance.getFx("USDGBP=X");
System.out.println(usdeur);
System.out.println(usdgbp);
```
Output:
```
USDEUR=X: 0.7842
USDGBP=X: 0.6253
```

## Single stock, include historical quotes (1)
```java
Stock tesla = YahooFinance.get("TSLA", true);
System.out.println(tesla.getHistory());
```
Output: (Symbol@Date: low-high, open->close (adjusted close))
```
[TSLA@2014-10-01: 217.32-265.54, 242.2->229.7 (229.7), TSLA@2014-09-02: 240.12-291.42, 275.5->242.68 (242.68), ...]
```

## Single stock, include historical quotes (2)
```java
Calendar from = Calendar.getInstance();
Calendar to = Calendar.getInstance();
from.add(Calendar.YEAR, -5); // from 5 years ago

Stock google = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);
```

## Multiple stocks, include historical quotes
```java
String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
// Can also be done with explicit from, to and Interval parameters
Map<String, Stock> stocks = YahooFinance.get(symbols, true);
Stock intel = stocks.get("INTC");
Stock airbus = stocks.get("AIR.PA");
```

## Alternatives for historical quotes
If the historical quotes are not yet available, the getHistory() method will automatically send a new request to Yahoo Finance.
```java
Stock google = YahooFinance.get("GOOG");
List<HistoricalQuote> googleHistQuotes = google.getHistory();
```
Or you could explicitly define the from, to and Interval parameters to force a new request.
Check the javadoc for more variations on the getHistory method
```java
Calendar from = Calendar.getInstance();
Calendar to = Calendar.getInstance();
from.add(Calendar.YEAR, -1); // from 1 year ago

Stock google = YahooFinance.get("GOOG");
List<HistoricalQuote> googleHistQuotes = google.getHistory(from, to, Interval.DAILY);
// googleHistQuotes is the same as google.getHistory() at this point
// provide some parameters to the getHistory method to send a new request to Yahoo Finance
```
