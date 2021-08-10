package POJO;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class OrderHistory {
    private String orderID;
    private LocalDate dateOfPurchase;
    private String customerID;
    private String[] stockIDs;
    private int[] counts;
    private long total;
    


    
   

    

    
    public OrderHistory(String orderID, LocalDate dateOfPurchase, String customerID, String[] stockIDs, int[] counts,
            long total) {
        this.orderID = orderID;
        this.dateOfPurchase = dateOfPurchase;
        this.customerID = customerID;
        this.stockIDs = stockIDs;
        this.counts = counts;
        this.total = total;
    }


    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }


    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }


    public String getOrderID() {
        return orderID;
    }


    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }


    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String[] getStockIDs() {
        return stockIDs;
    }
    public void setStockIDs(String[] stockIDs) {
        this.stockIDs = stockIDs;
    }
    
    public int[] getCounts() {
        return counts;
    }
    public void setCounts(int[] counts) {
        this.counts = counts;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("| %-20s |",orderID));
        builder.append(String.format(" %-20s |",dateOfPurchase.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))));
        builder.append(String.format(" %-15s |",customerID));
        builder.append(String.format(" %-30s |",Arrays.toString(stockIDs)));
        builder.append(String.format(" %-30s |",Arrays.toString(counts)));
        builder.append(String.format(" %-13d |",total));
        return builder.toString();
    }

}
