package Model.BaseInterface;

import java.util.List;


import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.Stock;
import Utilities.SortUtil.SortOrder;

public interface BaseStockDataBase{
    public List<Stock> getAllStockData();
    public Stock getStockData(String stockID) throws DataNotFoundException ;
    public void removeStockData(String stockID) throws CannotRemoveDataException, DataNotFoundException ;
    public void addStock(Stock newStock) throws CannotAddDataException;
    public long placeOrder(String stockID,int count) throws DataNotFoundException, InsufficientStockCountException;
    public void updateStock(String stockID,Stock.UpdateType updateType,String data) throws DataNotFoundException,NumberFormatException, CannotAddDataException, CannotRemoveDataException;
    public List<Stock> sortByStockCount(SortOrder sortOrder,List<Stock> currentStocks);
    public List<Stock> sortByStockPrice(SortOrder sortOrder,List<Stock> currentStocks);
    public List<Stock> filterByStockPrice(int filterPrice,List<Stock> currentStocks);
    public List<Stock> filterByStockName(String filter,List<Stock> currentStocks);
    

}