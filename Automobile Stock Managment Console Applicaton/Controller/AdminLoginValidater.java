package Controller;

import Controller.BaseInterface.BaseAdminLoginValidater;
import Controller.BaseInterface.BaseDataBaseController;
import CustomExceptions.InvalidLoginCredentialsException;

public class AdminLoginValidater implements BaseAdminLoginValidater {

    BaseDataBaseController dataBaseController;

    public AdminLoginValidater(){
        dataBaseController = DataBaseController.getInstance();
    }
    @Override
    public AdminController validateAdminLogin(String password) throws InvalidLoginCredentialsException {
        if(dataBaseController.checkAdminPassword(password)) return new AdminController();
        throw new InvalidLoginCredentialsException("Incorrect password");
    }
    
}
