package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import Model.BaseInterface.BaseStockDataBase;
import POJO.Stock;
import POJO.Stock.UpdateType;
import Utilities.SortUtil.SortOrder;

public class TestStockDB implements BaseStockDataBase {
    List<Stock> stocks ;

    public TestStockDB(){
        stocks = new ArrayList<>();

        stocks.add(new Stock("001", "dum1",500 ,500,10));
        stocks.add(new Stock("002", "dum2",10 ,500,10));
        stocks.add(new Stock("003", "dum3",100 ,500,10));
    }

    @Override
    public List<Stock> getAllStockData() {
        return stocks;
    }

    @Override
    public Stock getStockData(String stockID) throws DataNotFoundException{
        Stock stock = stocks.stream()
        .filter(order->order.getStockId().equals(stockID))
        .findFirst()
        .orElse(null);
        if(stock==null) throw new DataNotFoundException("Given StockID cannot be found");
        return stock;
    }

    @Override
    public boolean removeStockData(String stockID) throws DataNotFoundException, CannotRemoveDataException {
        if(stocks.remove(getStockData(stockID))) return true;
        
        throw new CannotRemoveDataException("Something went wrong! Cannot remove stock");
        
    }
    @Override
    public boolean addStock(Stock newStock) throws CannotAddDataException{
        if(stocks.add(newStock)) return true;
        throw new CannotAddDataException("Something went wrong! Canot add stock");
    }

    @Override
    public List<Stock> sortByStockCount(SortOrder sortOrder,List<Stock> currentStocks) {
        switch(sortOrder){
            case Ascending:
                            currentStocks.sort((stock1,stock2)->stock1.getStockDiscount()-stock2.getStockDiscount());
                            return stocks;
            case Descending:
                            currentStocks.sort((stock1,stock2)->stock2.getStockDiscount()-stock1.getStockDiscount());
                            return stocks;
            
        }
        return null;
    }

    @Override
    public List<Stock> sortByStockPrice(SortOrder sortOrder,List<Stock> currentStocks) {
        switch(sortOrder){
            case Ascending:
                            currentStocks.sort((stock1,stock2)->stock1.getStockPrice()-stock2.getStockPrice());
                            return stocks;
            case Descending:
                            currentStocks.sort((stock1,stock2)->stock2.getStockPrice()-stock1.getStockPrice());
                            return stocks;
        }
        return null;
    }

    @Override
    public List<Stock> filterByStockPrice(int filterPrice,List<Stock> currentStocks) {
        return currentStocks.stream().filter(stock->stock.getStockPrice()>=filterPrice).collect(Collectors.toList());
    }

    @Override
    public List<Stock> filterByStockName(String stockName,List<Stock> currentStocks) {
        String filter="(.*)"+stockName+"(.*)";
        List<Stock> temp = currentStocks.stream().filter(customer->customer.getStockName().matches(filter)).collect(Collectors.toList());
        return temp;
    }

    @Override
    public long placeOrder(String stockID, int count) throws DataNotFoundException, InsufficientStockCountException {
        Stock stock = getStockData(stockID);
        
        if(stock.getStockCount()>=count){stock.setStockCount(stock.getStockCount()-count);
            long stockCost = stock.getStockPrice()*count;
            stockCost-=(stockCost*stock.getStockDiscount()/100);
            return stockCost;
        }
        
        else{ throw new InsufficientStockCountException("Stock count is Insuffieient\n");}
    }

    @Override
    public boolean updateStock(String stockID,UpdateType updateType, String data) throws DataNotFoundException,NumberFormatException {
        Stock stock = getStockData(stockID);
       switch(updateType){
           case StockID:
                stock.setStockId(data);
                return true;
            case StockCount:
                stock.setStockCount(Integer.valueOf(data));
                return true;
            case StockDiscount:
                 stock.setStockDiscount(Integer.valueOf(data));
                 return true;
            case StockName:
                 stock.setStockName((String)data);
                 return true;
            case StockPrice:
                 stock.setStockPrice(Integer.valueOf(data));
                 return true;
       }
        return false;
    }

}
