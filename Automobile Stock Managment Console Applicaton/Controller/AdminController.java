package Controller;


import java.util.List;
import Controller.BaseInterface.BaseAdminController;
import Controller.BaseInterface.BaseDataBaseController;
import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.Customer;
import POJO.OrderHistory;
import POJO.Stock;
import POJO.Stock.UpdateType;
import Utilities.SortUtil.SortOrder;

public class AdminController implements BaseAdminController {

    private BaseDataBaseController dataBaseController;
    private List<Stock> latestStockQueryResult;
    private List<OrderHistory> latestOrderHistoryQueryResult;
    private List<Customer> latestCustomerQueryResult;

    AdminController() {
        dataBaseController = DataBaseController.getInstance();
       
    }

    @Override
    public List<Stock> getAllStock() {
        latestStockQueryResult = dataBaseController.getAllStockData();
        return latestStockQueryResult;
    }


    @Override
    public List<OrderHistory> getAllOrderHistory() {
        latestOrderHistoryQueryResult = dataBaseController.getAllOrderHistoryData();
        return latestOrderHistoryQueryResult;
    }

    @Override
    public List<Customer> getAllCustomerData() {
        latestCustomerQueryResult = dataBaseController.getAllCustomerData();
        return latestCustomerQueryResult;
    }

    @Override
    public List<Customer> sortCustomerByName(SortOrder sortOrder) {
        latestCustomerQueryResult = checkLatestCustomerQueryResults(latestCustomerQueryResult);
        return dataBaseController.sortByCustomerName(sortOrder, latestCustomerQueryResult);
    }

    @Override
    public List<Customer> filterCustomerByName(String filter) {
        latestCustomerQueryResult = checkLatestCustomerQueryResults(latestCustomerQueryResult);
        return dataBaseController.filterByCustomerName(filter, latestCustomerQueryResult);
    }

    @Override
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortOrder order) {
        latestOrderHistoryQueryResult = checkLatestOrderHistoryQueryResults(latestOrderHistoryQueryResult);
        latestOrderHistoryQueryResult = dataBaseController.sortOrderHistoryByTotalPrice(order, latestOrderHistoryQueryResult);
        return latestOrderHistoryQueryResult;
    }

    @Override
    public List<OrderHistory> filterOrderHistoryByStockID(String filter) {
        latestOrderHistoryQueryResult = checkLatestOrderHistoryQueryResults(latestOrderHistoryQueryResult);
        latestOrderHistoryQueryResult = dataBaseController.filterOrderHistoryByStockID(filter,latestOrderHistoryQueryResult);
        return latestOrderHistoryQueryResult;
    }

    @Override
    public List<Stock> sortStockbyPrice(SortOrder sortOrder) {
        latestStockQueryResult = checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.sortByStockPrice(sortOrder, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public List<Stock> sortStockbyCount(SortOrder sortOrder) {
        latestStockQueryResult = checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.sortByStockCount(sortOrder, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public List<Stock> filterStockByName(String filter) {
        latestStockQueryResult = checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.filterByStockName(filter, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public void addStock(Stock newStock) throws CannotAddDataException {
       dataBaseController.addStock(newStock);  
    }


    @Override
    public void createNewCustomer(Customer newCustomer) throws CannotAddDataException {
       dataBaseController.addCustomerData(newCustomer);
    }

    @Override
    public void updateStock(String stockID,UpdateType updateType, String data) throws NumberFormatException, DataNotFoundException {
        dataBaseController.updateStock(stockID,updateType,data);
        
    }

    @Override
    public void BuyStock(String stockID, int count) throws DataNotFoundException,InsufficientStockCountException {
        dataBaseController.BuyStock(stockID,count);
        
    }

    @Override
    public boolean removeOrderHistory(String orderHistoryID) throws DataNotFoundException, CannotRemoveDataException {
      return dataBaseController.removeOrderHistory(orderHistoryID);
    }

    @Override
    public boolean removeCustomer(String customerID) throws DataNotFoundException, CannotRemoveDataException {
        return dataBaseController.removeCustomer(customerID);
    }

    @Override
    public boolean removeStock(String stockID) throws DataNotFoundException, CannotRemoveDataException {
        return dataBaseController.removeStockData(stockID);
    }

    @Override
    public OrderHistory getOrderHistorybyID(String orderHistoryID) throws DataNotFoundException {
        
        return dataBaseController.getOrderHistoryData(orderHistoryID);
    }

    @Override
    public Stock getStockByID(String stockID) throws DataNotFoundException {
        return dataBaseController.getStockData(stockID);
    }

    @Override
    public Customer getCustomer(String customerID) throws DataNotFoundException {
        return dataBaseController.getCustomerData(customerID);
    }

    @Override
    public void clearAllFilter() {
      latestCustomerQueryResult=null;
      latestOrderHistoryQueryResult=null;
      latestStockQueryResult=null;
        
    }

    private List<Customer> checkLatestCustomerQueryResults(List<Customer> data){
        if(data==null || data.size()==0) data = getAllCustomerData();
        return data;
    }
    private List<OrderHistory> checkLatestOrderHistoryQueryResults(List<OrderHistory> data){
        if(data==null || data.size()==0) data = getAllOrderHistory();
        return data;
    }
    private List<Stock> checkLatestStockQueryResults(List<Stock> data){
        if(data==null || data.size()==0) data = getAllStock();
        return data;
    }
    

    
    
}
