package Controller.BaseInterface;

import CustomExceptions.DataNotFoundException;
import CustomExceptions.InvalidLoginCredentialsException;
import Model.BaseInterface.BaseCustomerDatabase;
import Model.BaseInterface.BaseOrderHistoryDataBase;
import Model.BaseInterface.BaseStockDataBase;
import POJO.Customer;

public interface BaseDataBaseController extends BaseCustomerDatabase,BaseOrderHistoryDataBase,BaseStockDataBase {
    public boolean checkAdminPassword(String password) throws InvalidLoginCredentialsException;
    public Customer checkCustomerCustomerIDAndPassword(String customerID,String password) throws InvalidLoginCredentialsException,DataNotFoundException;
    public boolean BuyStock(String stockID,int count) throws DataNotFoundException;
}
