package Utilities;

import java.util.List;
import POJO.*;

public class Printer {
    private static Printer INSTANCE;
    
    private String customerTablestr ;
    private String customerTableheader;
    private String orderHistoryTableStr;
    private String orderHistoryTableheader;
    private String StockTableStr;
    private String StockTableheader;

    private Printer(){
        //defaut private constructor.
    }

    public<T> void printObject(T obj){
        String decoration[] = getDecoration(obj);
        String tablestr=decoration[0];
        String borderStr=decoration[1];

        printTableTop(borderStr,tablestr);
        printMsg(obj);
        printborder(borderStr);

    }

    public <T> void printArray(List<T> objs){
        
        if(objs.size()==0){ printErrorMsg("No data available for given criteria");return;}
        
        String decoration[] = getDecoration(objs.get(0));
        String tablestr=decoration[0];
        String borderStr=decoration[1];
        
        printTableTop(borderStr,tablestr);
        for(Object o:objs){
            printMsg(o);
        }
        printborder(borderStr);
    
    }

    private <T> String[] getDecoration(T o){
        String[] decoration = new String[2];
        if(o instanceof Customer){
            decoration[0] = getCustomerTableString();
            decoration[1]=getCustomerTableheader();
        } 

        else if(o  instanceof OrderHistory){
            decoration[0] = getOrderHistoryTableString();
            decoration[1]=getOrderHistoryTableheader();
        }

        else if(o  instanceof Stock){
            decoration[0] = getStockTableString();
            decoration[1]=getStockTableheader();
        }
        return decoration;
    }

    


    private String getCustomerTableString(){
        if(customerTablestr==null){
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("| %-20s |","Customer_ID"));
            builder.append(String.format(" %-20s |","Name"));
            builder.append(String.format(" %-15s |","Password"));
            builder.append(String.format(" %-10s |","Gender"));
            builder.append(String.format(" %-20s |","DOB"));
            builder.append(String.format(" %-13s |","mobile_number"));
            builder.append(String.format(" %-10s |","is_Premium"));

            customerTablestr=builder.toString();
        }

        return customerTablestr;
    }

    private String getOrderHistoryTableString(){
        if(orderHistoryTableStr==null){
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("| %-20s |","Order_ID"));
            builder.append(String.format(" %-20s |","Date_OF_Purchase"));
            builder.append(String.format(" %-15s |","Customer_ID"));
            builder.append(String.format(" %-30s |","Stock_IDs"));
            builder.append(String.format(" %-30s |","Counts"));
            builder.append(String.format(" %-13s |","Total"));
            

            orderHistoryTableStr=builder.toString();
        }

        return orderHistoryTableStr;
    }

    private String getStockTableString(){
        if(StockTableStr==null){
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("| %-20s |","Stock_ID"));
            builder.append(String.format(" %-20s |","Stock_Name"));
            builder.append(String.format(" %-15s |","Stock_Price"));
            builder.append(String.format(" %-15s |","Stock_Count"));
            builder.append(String.format(" %-13s |","Discount"));

            StockTableStr=builder.toString();
        }

        return StockTableStr;
    }

    private String getCustomerTableheader(){
        if(customerTableheader==null){
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<getCustomerTableString().length();i++){
                builder.append("*");
            }
            customerTableheader=builder.toString();
        }

        return customerTableheader;
    }

    private String getOrderHistoryTableheader(){
        if(customerTableheader==null){
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<getOrderHistoryTableString().length();i++){
                builder.append("*");
            }
            orderHistoryTableheader=builder.toString();
        }

        return orderHistoryTableheader;
    }

    private String getStockTableheader(){
        if(StockTableheader==null){
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<getStockTableString().length();i++){
                builder.append("*");
            }
            StockTableheader=builder.toString();
        }

        return StockTableheader;
    }

    private void printborder(String headerStr){
        System.out.println(Colors.CYAN+headerStr+Colors.RESET);
    }
    private void printTableStr(String tableStr){
        System.out.println(Colors.YELLOW+tableStr+Colors.RESET);
    }

    private void printTableTop(String headerStr,String tableStr){
        printborder(headerStr);
        printTableStr(tableStr);
        printborder(headerStr);
    }

    public static Printer getInstance(){
        if(INSTANCE==null) INSTANCE = new Printer();
         return INSTANCE;
    }

    public void printErrorMsg(String msg){
        System.out.println(Colors.RED+msg+Colors.RESET);
    }
    public void printSuccessMsg(String msg){
        System.out.println(Colors.GREEN+msg+Colors.RESET);
    }
    public void printMsg(Object msg){
        System.out.println(Colors.YELLOW+msg+Colors.RESET);
    }

    
    
}
