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
    public void updateStock(String stockID,Stock.UpdateType updateType,String data) throws NumberFormatException, DataNotFoundException;
    public void BuyStock(String stockID,int count) throws DataNotFoundException, InsufficientStockCountException; 

    public boolean removeOrderHistory(String orderHistory) throws DataNotFoundException,CannotRemoveDataException;
    public boolean removeCustomer(String customerID)throws DataNotFoundException,CannotRemoveDataException;
    public boolean removeStock(String stouckID) throws DataNotFoundException,CannotRemoveDataException;
   
}
