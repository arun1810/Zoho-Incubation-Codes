package Controller;


import java.time.LocalDate;
import java.util.List;
import Controller.BaseInterface.BaseCustomerController;
import Controller.BaseInterface.BaseDataBaseController;
import CustomExceptions.CannotAddDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.*;
import Utilities.IdGenerator;
import Utilities.SortUtil.SortOrder;


public class CustomerController implements BaseCustomerController{
    private BaseDataBaseController dataBaseController;
    private final int premiumDiscount = 5;
    
    private List<Stock> latestStockQueryResult;
    private List<OrderHistory> latestOrderHistoryQueryResult;
    Customer customer;
    IdGenerator idGenerator;

         CustomerController(Customer customer){
            this.customer = customer;
            dataBaseController = DataBaseController.getInstance();
            idGenerator = IdGenerator.getInstance();
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        latestOrderHistoryQueryResult = dataBaseController.getOrderHistroyOfCustomer(customer.getCustomerId());
        return latestOrderHistoryQueryResult;
    }

    @Override
    public List<Stock> getAllStock() {
        latestStockQueryResult = dataBaseController.getAllStockData();
        return latestStockQueryResult;
    }

    @Override
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortOrder order) {
        latestOrderHistoryQueryResult=checkLatestOrderHistoryQueryResults(latestOrderHistoryQueryResult);
        latestOrderHistoryQueryResult = dataBaseController.sortOrderHistoryByTotalPrice(order,latestOrderHistoryQueryResult);
        return latestOrderHistoryQueryResult;
    }

  

   

    @Override
    public List<OrderHistory> filterOrderHistoryByStockID(String stockID) {
        latestOrderHistoryQueryResult=checkLatestOrderHistoryQueryResults(latestOrderHistoryQueryResult);
        latestOrderHistoryQueryResult = dataBaseController.filterOrderHistoryByStockID(stockID, latestOrderHistoryQueryResult);
        return latestOrderHistoryQueryResult;
    }

    @Override
    public List<Stock> sortStockbyPrice(SortOrder sortOrder) {
        latestStockQueryResult=checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.sortByStockPrice(sortOrder, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public List<Stock> sortStockbyCount(SortOrder sortOrder) {
        latestStockQueryResult=checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.sortByStockCount(sortOrder, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public List<Stock> filterStockByName(String stockName) {
        latestStockQueryResult=checkLatestStockQueryResults(latestStockQueryResult);
        latestStockQueryResult = dataBaseController.filterByStockName(stockName, latestStockQueryResult);
        return latestStockQueryResult;
    }

    @Override
    public void clearAllFilter() {
        latestOrderHistoryQueryResult=null;
        latestStockQueryResult=null;
        
    }

    @Override
    public long placeOrder(String stockID, int count) throws InsufficientStockCountException, DataNotFoundException{
        long stockCost = dataBaseController.placeOrder(stockID, count);
        stockCost = customer.isPremium() ? stockCost-(stockCost * premiumDiscount/100):stockCost;
        return stockCost;
        
    }

    @Override
    public Customer getPersonalInfo() {
        return customer;
        
    }

    @Override
    public String getHelloMessage() {
        
        return "Hello! "+customer.getName()+". what do you want to do today!";
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
    public void addOrder(List<String> stockIDs, List<Integer> stockCounts,long total) throws CannotAddDataException {
        String[] stockIds = new String[stockIDs.size()];
        int[] counts = new int[stockCounts.size()];
        int size=stockIds.length;
        for(int i=0;i<size;i++){
            stockIds[i] = stockIDs.get(i);
            counts[i] = stockCounts.get(i);
        }
        dataBaseController.addOrderHistoryData(new OrderHistory(idGenerator.generateID(), LocalDate.now(), customer.getCustomerId(), stockIds, counts, total));
        
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
