package Controller;

import Controller.BaseInterface.BaseCustomerLoginValidater;
import Controller.BaseInterface.BaseDataBaseController;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InvalidLoginCredentialsException;

public class CustomerLoginValidater implements BaseCustomerLoginValidater {
    private BaseDataBaseController dataBaseController;
    
    public CustomerLoginValidater(){
            dataBaseController = DataBaseController.getInstance();
        }

    @Override
    public CustomerController validateCustomerLogin(String customerID, String password) throws InvalidLoginCredentialsException, DataNotFoundException {
        
        return new CustomerController(dataBaseController.checkCustomerCustomerIDAndPassword(customerID, password));
    }
    
}
