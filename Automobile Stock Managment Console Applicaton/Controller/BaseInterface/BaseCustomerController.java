package Controller.BaseInterface;

import java.util.List;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.Customer;

public interface BaseCustomerController extends StockAndOrderHistoryViewer {
    
    public long placeOrder(String stockID,int count) throws DataNotFoundException,InsufficientStockCountException;
    public Customer getPersonalInfo();
    public String getHelloMessage();
    public void addOrder(List<String> stockIDs, List<Integer> stockCounts,long total) throws CannotAddDataException;
}
