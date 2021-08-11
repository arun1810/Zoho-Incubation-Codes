package View;

import java.io.BufferedReader;
import java.time.LocalDate;
import Controller.BaseInterface.BaseAdminController;
import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InsufficientStockCountException;
import POJO.Customer;
import POJO.Stock;
import POJO.Stock.UpdateType;
import Utilities.PatternChecker;
import Utilities.Printer;
import Utilities.UserDataGetter;
import View.BaseInterface.BaseView;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminView implements BaseView {
    private BaseAdminController adminController;
    private Printer printer;
    private UserDataGetter userDataGetter;
    private PatternChecker patternChecker;

    AdminView(BaseAdminController adminController,BufferedReader inputreader) {
        printer=Printer.getInstance();
        printer.printSuccessMsg("Admin logged in!!");
        this.adminController = adminController;
        userDataGetter = UserDataGetter.getInstance(inputreader);
        patternChecker = PatternChecker.getInstance();
    }
    @Override
    public void onStart(){
       String data;
       boolean canRunloop=true;
        do{
            int choice = userDataGetter.getIntegerDataFromUser("Enter a option\n1.Get all stocks\n2.Get all orderhistory\n3.Get all customer\n4.get a customer\n5.get a Stock\n6.get a OrderHistory\n7.Sort customer by name\n8.filter customer by name\n9.Sort orderhistory by totalprice\n10.Filter orderhistory by stockID\n11.Sort stock by price\n12.Filter stock by name \n13.Add stock\n14.Create new Customer\n15.Update Stock\n16.Buy stock\n17.Remove order history\n18.Remove customer\n19.Remove stock\n20.Clear all filters\n21.Logout");
            switch(choice){
                case 1:
                    printer.printArray(adminController.getAllStock());
                    break;
                case 2:
                    printer.printArray(adminController.getAllOrderHistory());
                    break;
                case 3:
                   printer.printArray(adminController.getAllCustomerData());
                   break;
                case 4:
                    data = userDataGetter.getStringDataFromUser("Enter customerID");
                    try {
                        printer.printObject(adminController.getCustomer(data));
                    } catch (DataNotFoundException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                    break;
                case 5:
                    data = userDataGetter.getStringDataFromUser("Enter StockID:");
                    try {
                        printer.printObject(adminController.getStockByID(data));
                    } catch (DataNotFoundException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                    break;
                case 6:
                    data = userDataGetter.getStringDataFromUser("Enter OrderHistoryID:");
                    try {
                        printer.printObject(adminController.getOrderHistorybyID(data));
                    } catch (DataNotFoundException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                    break;
                case 7:         
                    printer.printArray(adminController.sortCustomerByName(userDataGetter.getSortOrderFromUser()));
                    break;
                case 8:
                    data = userDataGetter.getStringDataFromUser("Enter filter:");
                    printer.printArray(adminController.filterCustomerByName(data));
                    break;
                case 9:
                    printer.printArray(adminController.sortOrderHistoryByTotalPrice(userDataGetter.getSortOrderFromUser()));
                    break;
                case 10:
                    data = userDataGetter.getStringDataFromUser("Enter stockID");
                    printer.printArray(adminController.filterOrderHistoryByStockID(data));
                    break;
                case 11:
                    printer.printArray(adminController.sortStockbyPrice(userDataGetter.getSortOrderFromUser()));
                    break;
                case 12:
                    data = userDataGetter.getStringDataFromUser("Enter filter:");
                    printer.printArray(adminController.filterStockByName(data));
                    break;
                case 13:
                        try {
                            adminController.addStock(createStockByUserInput());
                            printer.printSuccessMsg("Stocks Added Successfully");
                        } catch (CannotAddDataException e) {
                            printer.printErrorMsg(e.getMessage());
                        }
                break;
                case 14:
                
                    try {
                        adminController.createNewCustomer(createCustomerFromUserInput());
                        printer.printSuccessMsg("New customer Added Successfully");
                    } catch (CannotAddDataException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                break;
                case 15:
                    data = userDataGetter.getStringDataFromUser("Enter StockID:");
                    Stock.UpdateType updateType = getUpdateTypeFromUser();
                    String data2 = userDataGetter.getStringDataFromUser("Enter data to be Updated:");
                    try {
                        adminController.updateStock(data, updateType, data2);
                        printer.printSuccessMsg("Stocks updated Successfully");
                    } catch (NumberFormatException | DataNotFoundException| CannotAddDataException | CannotRemoveDataException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                    break;
                case 16:
                        data = userDataGetter.getStringDataFromUser("Enter stockID");
                        int count = userDataGetter.getIntegerDataFromUser("Enter stock count");
                    try {
                        adminController.BuyStock(data, count);
                        printer.printSuccessMsg("Stocks bought Successfully");
                    } catch (DataNotFoundException | InsufficientStockCountException| CannotAddDataException | NumberFormatException | CannotRemoveDataException e) {
                        printer.printErrorMsg(e.getMessage());
                    }
                    break;
                case 17:
                        data = userDataGetter.getStringDataFromUser("Enter OrderHistoryID");
                        try {
                            adminController.removeOrderHistory(data);
                            printer.printSuccessMsg("Order History removed Successfully");
                        } catch (DataNotFoundException | CannotRemoveDataException e) {
                            printer.printErrorMsg(e.getMessage());
                        }
                        break;
                case 18:
                        data = userDataGetter.getStringDataFromUser("Enter CustomerID");
                        try {
                            adminController.removeCustomer(data);
                            printer.printSuccessMsg("Customer removed Successfully");
                        } catch (DataNotFoundException | CannotRemoveDataException e) {
                            printer.printErrorMsg(e.getMessage());
                        }
                        break;
                case 19:
                        data = userDataGetter.getStringDataFromUser("Enter OrderHistoryID");
                        try {
                            adminController.removeStock(data);
                            printer.printSuccessMsg("Stock removed Successfully");
                        } catch (DataNotFoundException | CannotRemoveDataException e) {
                            printer.printErrorMsg(e.getMessage());
                        }
                        break;
                case 20:
                        adminController.clearAllFilter();
                        printer.printSuccessMsg("All filters cleared");
                        break;
                case 21:
                        canRunloop=false;
                        printer.printSuccessMsg("You have successfully loged out");
                        break;
                default:
                        printer.printErrorMsg("Please enter a valid input");
                        break;
            }
        }
        while(canRunloop);
       
    }
    private Stock createStockByUserInput(){
        String stockName = userDataGetter.getStringDataFromUser("Enter stockName");
        String stockID = userDataGetter.getStringDataFromUser("Enter stockID");
        int stockPrice = userDataGetter.getIntegerDataFromUser("Enter stockPrice");
        int stockCount = userDataGetter.getIntegerDataFromUser("Enter stockCount");
        int stockDiscount = userDataGetter.getIntegerDataFromUser("Enter stockDiscount");
        return new Stock(stockID,stockName,stockPrice,stockCount,stockDiscount);
        
    }
    private Stock.UpdateType getUpdateTypeFromUser(){
        int choice = userDataGetter.getIntegerDataFromUser("What do you want to update?\n1.stockID\n2.StockName\n3.StockPrice\n4.StockCount\n5.StockDiscount");
        Stock.UpdateType updateType=UpdateType.StockName;
        switch(choice){
                case 1:updateType = Stock.UpdateType.StockID;break;
                case 2:updateType = Stock.UpdateType.StockName;break;
                case 3:updateType = Stock.UpdateType.StockPrice;break;
                case 4:updateType = Stock.UpdateType.StockCount;break;
                case 5:updateType = Stock.UpdateType.StockDiscount;break;
                default:printer.printErrorMsg("Enter a valid option");
                }
        return updateType;
    }
    private Customer createCustomerFromUserInput(){
        
        String customerID = customerIDgetter();
        String customerName = userDataGetter.getStringDataFromUser("Enter CustomerName");
        String customerPassword = userDataGetter.getStringDataFromUser("Enter CustomerPassword");
        char customerGender =userDataGetter.getStringDataFromUser("Enter customer gender M-male F-felame").toCharArray()[0];
        LocalDate customerDOB = customerDobGetter();
        String customerMobileNo = customerMobileNumberGetter();
        int choice = userDataGetter.getIntegerDataFromUser("is customer premium? 1-yes 2-No");
        boolean ispremium = choice==1 ? true:false;
        return new Customer(customerID,customerName,customerPassword,customerGender,customerDOB,customerMobileNo,ispremium);
    }

    private String customerIDgetter(){
        boolean isCustomerIdNotUnique = true;
        String customerID ="";
        do{
            try{customerID= userDataGetter.getStringDataFromUser("Enter CustomerID");
                adminController.getCustomer(customerID);
            }
            catch(DataNotFoundException e){
                isCustomerIdNotUnique = false;
            }
        }
        while(isCustomerIdNotUnique);

        return customerID;
    }

    private LocalDate customerDobGetter(){
        boolean isDobNotValid = true;
        String dobStr;
        LocalDate date = LocalDate.now();
        do{
            dobStr = userDataGetter.getStringDataFromUser("Enter Customer DOB in dd-mm-yyyy format");
            if(patternChecker.checkDOB(dobStr)) isDobNotValid = false;
            else printer.printErrorMsg("DOB format is not correct");
            try{date = LocalDate.parse(dobStr,DateTimeFormatter.ofPattern("dd-MM-yyyy"));}
            catch(DateTimeParseException e){
                isDobNotValid = true;
                printer.printErrorMsg("DOB format is not correct");
            }
        }
        while(isDobNotValid);
        return date;
    }

    private String customerMobileNumberGetter(){
        boolean isMobileNumberNotValid = true;
        String mobileNumber;
        do{
            mobileNumber = userDataGetter.getStringDataFromUser("Enter Mobile number");
            if(patternChecker.checkMobileNumber(mobileNumber)) isMobileNumberNotValid = false;
            else printer.printErrorMsg("Mobile number format is not correct");
        }while(isMobileNumberNotValid);
        return mobileNumber;
    }
}
