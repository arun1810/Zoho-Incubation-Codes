package View;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import Controller.BaseInterface.BaseCustomerController;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import Utilities.Printer;
import Utilities.UserDataGetter;
import View.BaseInterface.BaseView;


public class CustomerView implements BaseView {
    private BaseCustomerController customerController;
    private Printer printer;
    private UserDataGetter userDataGetter;
   
    CustomerView(BaseCustomerController customerController,BufferedReader inputReader){
       this.customerController = customerController;
       printer = Printer.getInstance();
       printer.printSuccessMsg(customerController.getHelloMessage());
       userDataGetter=UserDataGetter.getInstance(inputReader);
    }

    public void onStart(){
        String data;
        boolean canRunloop=true;
        do{
            int choice=userDataGetter.getIntegerDataFromUser("Enter a option\n1.Get all stocks\n2.Get all orderhistory\n3.Sort orderhistory by total price\n4.filter orderhistory by stockID\n5.Sort stock by price\n6.Sort stock by count\n7.Filter stock by name\n8.Clear all filter\n9.Place order\n10.Get personal info\n11.logout");

            switch(choice){
                case 1:
                    printer.printArray(customerController.getAllStock());
                    break;
                case 2:
                    printer.printArray(customerController.getAllOrderHistory());
                    break;
                case 3:
                    printer.printArray(customerController.sortOrderHistoryByTotalPrice(userDataGetter.getSortOrderFromUser()));
                    break;
                case 4:
                    data = userDataGetter.getStringDataFromUser("Enter StockID");
                    printer.printArray(customerController.filterOrderHistoryByStockID(data));
                    break;
                case 5:
                    printer.printArray(customerController.sortStockbyPrice(userDataGetter.getSortOrderFromUser()));
                    break;
                case 6:
                    printer.printArray(customerController.sortStockbyCount(userDataGetter.getSortOrderFromUser()));
                    break;
                case 7:
                    data = userDataGetter.getStringDataFromUser("enter filter name");
                    printer.printArray(customerController.filterStockByName(data));
                    break;
                case 8:
                    customerController.clearAllFilter();
                    break;
                case 9:
                    purchase();
                    break;
                case 10:
                    printer.printObject(customerController.getPersonalInfo());
                    break;
                case 11:
                    canRunloop=false;
                    printer.printSuccessMsg("You have successfully Loged out");
                    break;
                default:
                    printer.printErrorMsg("Please enter a valid input");
                    break;
            }
        }while(canRunloop);
    }
  
    private void purchase(){
        String data;
        boolean canContinueLoop = true;
        List<String> stockIDs = new ArrayList<>();
        List<Integer> stockCounts = new ArrayList<>();
        long total=0;

        do{
            data = userDataGetter.getStringDataFromUser("Enter stockID:");
            int count =userDataGetter.getIntegerDataFromUser("Enter stock count");
            try {
                total+= customerController.placeOrder(data, count);
                if(count!=0){
                    stockIDs.add(data);
                    stockCounts.add(count);
                }
                else{ printer.printErrorMsg("stock Count should be greater than 0");}
                
                } catch (DataNotFoundException | InsufficientStockCountException e) {
                    printer.printErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
                canContinueLoop= userDataGetter.getStringDataFromUser("Continue purchase? y/n").toLowerCase().toCharArray()[0]=='y';
                }
                while(canContinueLoop);
                    if(stockCounts.size()!=0){
                        int billingChoice=0;//default no
                        billingChoice = userDataGetter.getIntegerDataFromUser("Confirm Order? 1-yes 2-No");
                        if(billingChoice==1)  {
                                customerController.addOrder(stockIDs, stockCounts, total);  
                                printer.printSuccessMsg("Hurray! Order placed Successfully!!");
                            }
                        else printer.printErrorMsg("Purchase aborted");
                    }
                    
    }
                    
}
