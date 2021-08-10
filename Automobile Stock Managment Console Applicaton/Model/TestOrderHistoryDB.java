package Model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Model.BaseInterface.BaseOrderHistoryDataBase;
import POJO.OrderHistory;
import Utilities.SortUtil.SortOrder;
import java.util.stream.Collectors;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;

public class TestOrderHistoryDB implements BaseOrderHistoryDataBase {
    List<OrderHistory> orderHistories;

    public TestOrderHistoryDB(){
        orderHistories = new ArrayList<>();
        orderHistories.add(new OrderHistory("100",LocalDate.now(),"1",new String[]{"001","002","003"}, new int[]{1,20,3}, 1000));
        orderHistories.add(new OrderHistory("101",LocalDate.now(),"2",new String[]{"001","002","003"}, new int[]{1,20,3}, 1000));
        orderHistories.add(new OrderHistory("102",LocalDate.now(),"3",new String[]{"001","002","003"}, new int[]{1,20,3}, 1000));
        orderHistories.add(new OrderHistory("103",LocalDate.now(),"4",new String[]{"001","002","003"}, new int[]{1,20,3}, 1000));
    }

    @Override
    public List<OrderHistory> getAllOrderHistoryData() {
        
        return orderHistories;
    }

    @Override
    public OrderHistory getOrderHistoryData(String orderID) throws DataNotFoundException {
        OrderHistory orderHistory = orderHistories.stream()
                                                  .filter(order->order.getOrderID().equals(orderID))
                                                  .findFirst()
                                                  .orElse(null);
        
        if(orderHistory==null) throw new DataNotFoundException("OrderHistoryID cannot be found\n");
        return orderHistory;
    }

    

    @Override
    public boolean addOrderHistoryData(OrderHistory orderHistory)  {
        
        if(orderHistories.add(orderHistory)) return true;
        return false;
    }

    @Override
    public List<OrderHistory> getOrderHistroyOfCustomer(String customerID)  {
        
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
    public boolean removeOrderHistory(String orderHistoryID) throws CannotRemoveDataException, DataNotFoundException {
        if(orderHistories.remove(getOrderHistoryData(orderHistoryID))) return true;
        throw new CannotRemoveDataException("Something went wrong! Cannot remove OrderHistory\n");
    }
    
}
