package Controller;


import java.time.LocalDate;
import java.util.List;
import Controller.BaseInterface.BaseDataBaseController;
import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import CustomExceptions.InvalidLoginCredentialsException;
import Model.TestCustomerDB;
import Model.TestOrderHistoryDB;
import Model.TestStockDB;
import Model.BaseInterface.BaseCustomerDatabase;
import Model.BaseInterface.BaseOrderHistoryDataBase;
import Model.BaseInterface.BaseStockDataBase;
import POJO.Customer;
import POJO.OrderHistory;
import POJO.Stock;
import POJO.Stock.UpdateType;
import Utilities.SortUtil;
import Utilities.SortUtil.SortOrder;

public class DataBaseController implements BaseDataBaseController {
    
    private BaseCustomerDatabase customerDatabase;
    private BaseOrderHistoryDataBase orderHistoryDataBase;
    private BaseStockDataBase stockDataBase;
    private final String AdminPassword = "Admin1234";
    private static DataBaseController Instance;

    private DataBaseController(){
        this.customerDatabase=new TestCustomerDB();
        this.orderHistoryDataBase = new TestOrderHistoryDB();
        this.stockDataBase = new TestStockDB();
    }
//CustomerDataBase quries
    @Override
    public List<Customer> getAllCustomerData(){
        return customerDatabase.getAllCustomerData();
    }
    @Override
    public Customer getCustomerData(String customerID) throws DataNotFoundException{
        
        return customerDatabase.getCustomerData(customerID);

    }
   
    @Override
    public boolean addCustomerData(Customer customer) throws CannotAddDataException{
        return customerDatabase.addCustomerData(customer);
    }
    
    @Override
    public boolean removeCustomer(String customerID) throws DataNotFoundException, CannotRemoveDataException {
        return customerDatabase.removeCustomer(customerID);
    }
    @Override
    public List<Customer> sortByCustomerName(SortOrder sortOrder, List<Customer> currentCustomers) {
        return customerDatabase.sortByCustomerName(sortOrder, currentCustomers);
    }
    @Override
    public List<Customer> filterByCustomerName(String filter, List<Customer> currentCustomers) {
        return customerDatabase.filterByCustomerName(filter, currentCustomers);
    }
//OrderHistory quries
    @Override
    public List<OrderHistory> getAllOrderHistoryData(){
        return orderHistoryDataBase.getAllOrderHistoryData();
    }
    @Override
    public OrderHistory getOrderHistoryData(String OrderID) throws DataNotFoundException{
        
        return orderHistoryDataBase.getOrderHistoryData(OrderID);
    }
    
    @Override
    public boolean addOrderHistoryData(OrderHistory orderHistory){
       
        return orderHistoryDataBase.addOrderHistoryData(orderHistory);
    }
    @Override
    public List<OrderHistory> getOrderHistroyOfCustomer(String customerID){
      
        return orderHistoryDataBase.getOrderHistroyOfCustomer(customerID);
    }
    
    @Override
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortUtil.SortOrder order,
            List<OrderHistory> latestOrderHistoryQueryResult) {
        
        return orderHistoryDataBase.sortOrderHistoryByTotalPrice(order, latestOrderHistoryQueryResult);
    }
    @Override
    public List<OrderHistory> sortOrderHistoryByDate(SortUtil.SortOrder sortOrder,
            List<OrderHistory> currentOrderHistories) {
        
        return orderHistoryDataBase.sortOrderHistoryByDate(sortOrder, currentOrderHistories);
    }

    
    @Override
    public boolean removeOrderHistory(String orderHistoryID)
            throws CannotRemoveDataException, DataNotFoundException {
        return orderHistoryDataBase.removeOrderHistory(orderHistoryID);
    }
    //Stock Database quries
    @Override
    public List<Stock> getAllStockData(){
        return stockDataBase.getAllStockData();
    }
    @Override
    public Stock getStockData(String stockID) throws DataNotFoundException{
       return stockDataBase.getStockData(stockID);
    }
    @Override
    public boolean removeStockData(String stockID) throws CannotRemoveDataException, DataNotFoundException{
        return stockDataBase.removeStockData(stockID);
    }
    
    @Override
    public boolean addStock(Stock newStock) throws CannotAddDataException {
        return stockDataBase.addStock(newStock);
    }

    
    @Override
    public boolean updateStock(String stockID, UpdateType updateType, String data)
            throws DataNotFoundException, NumberFormatException {
       
        return stockDataBase.updateStock(stockID, updateType, data);
    }
    @Override
    public List<Stock> sortByStockCount(SortUtil.SortOrder sortOrder,List<Stock> currentStocks){
        return stockDataBase.sortByStockCount(sortOrder,currentStocks);
    }
    @Override
    public List<Stock> sortByStockPrice(SortUtil.SortOrder sortOrder,List<Stock> currentStocks){
        return stockDataBase.sortByStockPrice(sortOrder,currentStocks);
    }
    @Override
    public List<OrderHistory> filterOrderHistoryByStockID(String filter,
            List<OrderHistory> currentOrderHistories) {
                
        return orderHistoryDataBase.filterOrderHistoryByStockID(filter, currentOrderHistories);
    }
    @Override
    public List<OrderHistory> filterOrderHistoryByCustomerID(String filter,
            List<OrderHistory> currentOrderHistories) {
        
        return orderHistoryDataBase.filterOrderHistoryByCustomerID(filter, currentOrderHistories);
    }
    @Override
    public List<OrderHistory> filterOrderHistoryByDateOfPurchase(LocalDate filterDate,
            List<OrderHistory> currentOrderHistories) {
        
        return orderHistoryDataBase.filterOrderHistoryByDateOfPurchase(filterDate, currentOrderHistories);
    }
    @Override
    public List<Stock> filterByStockPrice(int filterPrice, List<Stock> currentStocks) {
        return stockDataBase.filterByStockPrice(filterPrice, currentStocks);
        
    }
    @Override
    public List<Stock> filterByStockName(String filter, List<Stock> currentStocks) {
        
        return stockDataBase.filterByStockName(filter, currentStocks);
    }
    
    @Override
    public boolean checkAdminPassword(String password) throws InvalidLoginCredentialsException {
        if(password.equals(AdminPassword)) return true;
        throw new InvalidLoginCredentialsException("Admin password is incorrect\n");
    }

    @Override
    public long placeOrder(String stockID, int count) throws DataNotFoundException,InsufficientStockCountException {
        return stockDataBase.placeOrder(stockID, count);
    }

    public static DataBaseController getInstance(){
        if(Instance==null){
            Instance = new DataBaseController();
        }
        return Instance;
    }
    @Override
    public Customer checkCustomerCustomerIDAndPassword(String customerID, String password)
            throws InvalidLoginCredentialsException, DataNotFoundException {
                
                Customer customer = customerDatabase.getCustomerData(customerID);
                if(customer.getPassword().equals(password)) return customer;
                throw new InvalidLoginCredentialsException("Inalid logins credentials\n");
    }
    @Override
    public boolean BuyStock(String stockID,int count) throws DataNotFoundException {
        Stock stock = getStockData(stockID);
        count+=stock.getStockCount();
        updateStock(stockID, Stock.UpdateType.StockCount, String.valueOf(count));
        return false;
    }
   

}
