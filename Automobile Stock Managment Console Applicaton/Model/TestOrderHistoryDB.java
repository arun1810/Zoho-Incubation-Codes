package Model;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Model.BaseInterface.BaseOrderHistoryDataBase;
import POJO.OrderHistory;
import Utilities.JsonDB;
import Utilities.SortUtil.SortOrder;
import java.util.stream.Collectors;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;

public class TestOrderHistoryDB implements BaseOrderHistoryDataBase {
    private List<OrderHistory> orderHistories;
    private File orderHistoryDbFile;
    private JsonDB jsonDB;

    public TestOrderHistoryDB(File orderHistoryDbFile){
        this.orderHistoryDbFile = orderHistoryDbFile;
        jsonDB = JsonDB.getInstance();
    }

    @Override
    public List<OrderHistory> getAllOrderHistoryData() {
        orderHistories = jsonDB.getAllDataFromFile(orderHistoryDbFile, OrderHistory.class);
        return orderHistories;
    }

    @Override
    public OrderHistory getOrderHistoryData(String orderID) throws DataNotFoundException {
        if(orderHistories==null) orderHistories = getAllOrderHistoryData();
        
        OrderHistory orderHistory = orderHistories.stream()
                                                  .filter(order->order.getOrderID().equals(orderID))
                                                  .findFirst()
                                                  .orElse(null);
        
        if(orderHistory==null) throw new DataNotFoundException("OrderHistoryID cannot be found\n");
        return orderHistory;
    }

    

    @Override
    public void addOrderHistoryData(OrderHistory orderHistory) throws CannotAddDataException  {
        orderHistories = jsonDB.writeDataToDataBase(orderHistory, orderHistoryDbFile,OrderHistory.class);
    }

    @Override
    public List<OrderHistory> getOrderHistroyOfCustomer(String customerID)  {
        if(orderHistories==null) orderHistories = getAllOrderHistoryData();
        return orderHistories.stream()
                             .filter(order->order.getCustomerID().equals(customerID))
                             .collect(Collectors.toList());
                            

    }

    @Override
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortOrder sortOrder,List<OrderHistory> currentOrderHistories) {
        switch(sortOrder){
            case Ascending:
                            
                            currentOrderHistories.sort((order1,order2)->(int)(order1.getTotal()-order2.getTotal()));
                            return  currentOrderHistories;
            
            case Descending:
                           currentOrderHistories.sort((order1,order2)->(int)(order2.getTotal()-order1.getTotal()));
                           return currentOrderHistories;
        }
        return null;
    }

    @Override
    public List<OrderHistory> sortOrderHistoryByDate(SortOrder sortOrder,List<OrderHistory> currentOrderHistories) {
        switch(sortOrder){
            case Ascending:
                            currentOrderHistories.sort((order1,order2)->order1.getDateOfPurchase().compareTo(order2.getDateOfPurchase()));
                            return  currentOrderHistories;
            
            case Descending:
                            currentOrderHistories.sort((order1,order2)->order2.getDateOfPurchase().compareTo(order1.getDateOfPurchase()));
                            return currentOrderHistories;
        }
        return null;
    }

    @Override
    public List<OrderHistory> filterOrderHistoryByStockID(String filter,
            List<OrderHistory> currentOrderHistories) {
                List<OrderHistory> result = new ArrayList<>();
                
                for(OrderHistory orderHistory:currentOrderHistories){
                        for(String stockId:orderHistory.getStockIDs()){
                            if(stockId.equals(filter)){
                                result.add(orderHistory);
                                break;}
                        }
                }
        return result;
    }

    @Override
    public List<OrderHistory> filterOrderHistoryByCustomerID(String customerID,List<OrderHistory> currentOrderHistories) {
        String filter="(.*)"+customerID+"(.*)";
        return  currentOrderHistories.stream().filter(orderHistory->orderHistory.getCustomerID().matches(filter)).collect(Collectors.toList());
        
    }

    @Override
    public List<OrderHistory> filterOrderHistoryByDateOfPurchase(LocalDate filterDate,
            List<OrderHistory> currentOrderHistories) {
    
        return currentOrderHistories.stream().filter(orderHistory->orderHistory.getDateOfPurchase().isAfter(filterDate) && orderHistory.getDateOfPurchase().equals(filterDate)).collect(Collectors.toList());
    }

    @Override
    public void removeOrderHistory(String orderHistoryID) throws CannotRemoveDataException, DataNotFoundException {
        orderHistories = jsonDB.removeDataFromDataBase(getOrderHistoryData(orderHistoryID), orderHistoryDbFile, OrderHistory.class);
    }
    
}
