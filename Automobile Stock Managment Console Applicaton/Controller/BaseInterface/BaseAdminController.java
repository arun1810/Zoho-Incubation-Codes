package Controller.BaseInterface;

import java.util.List;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.*;
import Utilities.SortUtil.SortOrder;


public interface BaseAdminController extends StockAndOrderHistoryViewer{
    
    public void createNewCustomer(Customer newCustomer) throws CannotAddDataException;
    public List<Customer> getAllCustomerData();
    public Customer getCustomer(String customerID) throws DataNotFoundException;
    public List<Customer> sortCustomerByName(SortOrder sortOrder);
    public List<Customer> filterCustomerByName(String filter);

    public void addStock(Stock newStock)throws CannotAddDataException;
    public void updateStock(String stockID,Stock.UpdateType updateType,String data) throws NumberFormatException, DataNotFoundException, CannotAddDataException, CannotRemoveDataException;
    public void BuyStock(String stockID,int count) throws DataNotFoundException, InsufficientStockCountException, CannotAddDataException, NumberFormatException, CannotRemoveDataException; 

    public void removeOrderHistory(String orderHistory) throws DataNotFoundException,CannotRemoveDataException;
    public void removeCustomer(String customerID)throws DataNotFoundException,CannotRemoveDataException;
    public void removeStock(String stouckID) throws DataNotFoundException,CannotRemoveDataException;
   
}
