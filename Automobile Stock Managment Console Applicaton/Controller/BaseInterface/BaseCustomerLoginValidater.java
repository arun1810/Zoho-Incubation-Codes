package Controller.BaseInterface;

import Controller.CustomerController;
import CustomExceptions.DataNotFoundException;
import CustomExceptions.InvalidLoginCredentialsException;

public interface BaseCustomerLoginValidater {
    public CustomerController validateCustomerLogin(String CustomerID, String password) throws InvalidLoginCredentialsException, DataNotFoundException;
}
