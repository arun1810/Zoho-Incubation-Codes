package Model.BaseInterface;

import java.time.LocalDate;
import java.util.List;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import POJO.OrderHistory;
import Utilities.SortUtil;


public interface BaseOrderHistoryDataBase {
    
    public List<OrderHistory> getAllOrderHistoryData();
    public OrderHistory getOrderHistoryData(String OrderID) throws DataNotFoundException ;
    public void addOrderHistoryData(OrderHistory orderHistory) throws CannotAddDataException;
    public void removeOrderHistory(String orderHistoryID) throws CannotRemoveDataException, DataNotFoundException;
    public List<OrderHistory> getOrderHistroyOfCustomer(String customerID) ;
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortUtil.SortOrder sortOrder,List<OrderHistory> latestOrderHistoryQueryResult);
    public List<OrderHistory> sortOrderHistoryByDate(SortUtil.SortOrder sortOrder,List<OrderHistory> currentOrderHistories);
    public List<OrderHistory> filterOrderHistoryByStockID(String filter,List<OrderHistory> currentOrderHistories);
    public List<OrderHistory> filterOrderHistoryByCustomerID(String filter,List<OrderHistory> currentOrderHistories);
    public List<OrderHistory> filterOrderHistoryByDateOfPurchase(LocalDate filterDate,List<OrderHistory> currentOrderHistories);
}
