package Model;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import Model.BaseInterface.BaseStockDataBase;
import POJO.Stock;
import POJO.Stock.UpdateType;
import Utilities.JsonDB;
import Utilities.SortUtil.SortOrder;

public class TestStockDB implements BaseStockDataBase {
    private List<Stock> stocks ;
    private File stockDbFile;
    private JsonDB jsonDB;

    public TestStockDB(File stockDbFile){
        this.stockDbFile = stockDbFile;
        jsonDB = JsonDB.getInstance();
    }

    @Override
    public List<Stock> getAllStockData() {
        stocks = jsonDB.getAllDataFromFile(stockDbFile, Stock.class);
        return stocks;
    }

    @Override
    public Stock getStockData(String stockID) throws DataNotFoundException{
        if(stocks==null) stocks = getAllStockData();
        
        Stock stock = stocks.stream()
        .filter(order->order.getStockId().equals(stockID))
        .findFirst()
        .orElse(null);
        if(stock==null) throw new DataNotFoundException("Given StockID cannot be found");
        return stock;
    }

    @Override
    public void removeStockData(String stockID) throws DataNotFoundException,CannotRemoveDataException {
        stocks = jsonDB.removeDataFromDataBase(getStockData(stockID), stockDbFile, Stock.class);
        
    }
    @Override
    public void addStock(Stock newStock) throws CannotAddDataException{
        stocks = jsonDB.writeDataToDataBase(newStock,stockDbFile,Stock.class);
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
    public void updateStock(String stockID,UpdateType updateType, String data) throws DataNotFoundException,NumberFormatException,CannotAddDataException, CannotRemoveDataException {
        Stock stock = getStockData(stockID);
        stocks = jsonDB.removeDataFromDataBase(stock,stockDbFile,Stock.class);
       switch(updateType){
           case StockID:
                stock.setStockId(data);
                break;
                
            case StockCount:
                stock.setStockCount(Integer.valueOf(data));
                break;
                
            case StockDiscount:
                 stock.setStockDiscount(Integer.valueOf(data));
                 break;
                 
            case StockName:
                 stock.setStockName((String)data);
                 break;
                 
            case StockPrice:
                 stock.setStockPrice(Integer.valueOf(data));
                 break;
                 
       }
       stocks.add(stock);
       jsonDB.updateDataOnDb(stocks,stockDbFile);
    }

}
