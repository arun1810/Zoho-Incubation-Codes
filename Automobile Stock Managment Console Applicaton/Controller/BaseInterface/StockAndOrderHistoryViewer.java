package Controller.BaseInterface;

import java.util.List;

import CustomExceptions.DataNotFoundException;
import POJO.OrderHistory;
import POJO.Stock;
import Utilities.SortUtil;
import Utilities.SortUtil.SortOrder;

public interface StockAndOrderHistoryViewer {
    public List<OrderHistory> getAllOrderHistory();
    public List<OrderHistory> sortOrderHistoryByTotalPrice(SortOrder order);
    public List<OrderHistory> filterOrderHistoryByStockID(String filter);
    public OrderHistory getOrderHistorybyID(String orderHistoryID) throws DataNotFoundException;

    public List<Stock> getAllStock();
    public Stock getStockByID(String stockID) throws DataNotFoundException;
    public List<Stock> sortStockbyPrice(SortUtil.SortOrder sortOrder);
    public List<Stock> sortStockbyCount(SortUtil.SortOrder sortOrder);
    public List<Stock> filterStockByName(String filter);

    public void clearAllFilter();
    
}
