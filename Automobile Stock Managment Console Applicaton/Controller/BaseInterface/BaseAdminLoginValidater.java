package Controller.BaseInterface;

import Controller.AdminController;
import CustomExceptions.InvalidLoginCredentialsException;

public interface BaseAdminLoginValidater {
    public AdminController validateAdminLogin(String password) throws InvalidLoginCredentialsException;
}
