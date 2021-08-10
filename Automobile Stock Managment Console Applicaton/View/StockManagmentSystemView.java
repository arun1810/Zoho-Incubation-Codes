package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Controller.AdminLoginValidater;
import Controller.CustomerLoginValidater;
import Controller.BaseInterface.BaseAdminController;
import Controller.BaseInterface.BaseAdminLoginValidater;
import Controller.BaseInterface.BaseCustomerController;
import Controller.BaseInterface.BaseCustomerLoginValidater;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InvalidLoginCredentialsException;
import Utilities.Printer;
import Utilities.UserDataGetter;
import View.BaseInterface.BaseView;

public class StockManagmentSystemView implements BaseView {
    BufferedReader inputReader;
    Printer printer;
    UserDataGetter userDataGetter;
    StockManagmentSystemView(){
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        printer = Printer.getInstance();
        userDataGetter = UserDataGetter.getInstance(inputReader);

    }

    public void onStart(){
        boolean canRunLoop = true;
       do{
           int choice= userDataGetter.getIntegerDataFromUser("1.Login As Customer\n2.Login As Admin\n3.Exit");
       
            if(choice==1) askForCustomerCredentials();
            else if(choice==2) askForAdminCredentials();
            else if(choice==3) {canRunLoop=false;printer.printSuccessMsg("See you again!");}
            else{printer.printErrorMsg("Invalid Option");}
        }
        while(canRunLoop);
        closeAll();
    }

   private void askForCustomerCredentials(){
        String customerID = userDataGetter.getStringDataFromUser("Enter Your ID");
        String password = userDataGetter.getStringDataFromUser("Enter Your password");

        try {
            BaseCustomerLoginValidater customerLoginValidater = new CustomerLoginValidater();
            BaseCustomerController  customerController = customerLoginValidater.validateCustomerLogin(customerID, password);
            BaseView customerView = new CustomerView(customerController,inputReader);
            customerView.onStart();
        } catch (DataNotFoundException | InvalidLoginCredentialsException e) {
            e.printStackTrace();
            printer.printErrorMsg("Invalid login credentials");
        }
    }

    private void askForAdminCredentials(){

        String password = userDataGetter.getStringDataFromUser("Enter Your Password");

        try {
           BaseAdminLoginValidater adminLoginValidater = new AdminLoginValidater();
           BaseAdminController adminController = adminLoginValidater.validateAdminLogin(password);
           BaseView adminView =  new AdminView(adminController,inputReader);
           adminView.onStart();

        } catch (InvalidLoginCredentialsException e) {
            printer.printErrorMsg(e.getMessage());
        } 
    }
    private void closeAll(){
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
