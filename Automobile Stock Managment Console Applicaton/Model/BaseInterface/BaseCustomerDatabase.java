package Model.BaseInterface;
import java.util.List;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import POJO.Customer;
import Utilities.SortUtil;

public interface BaseCustomerDatabase {
    public List<Customer> getAllCustomerData();
    public Customer getCustomerData(String customerID) throws DataNotFoundException;
    public boolean addCustomerData(Customer customer) throws CannotAddDataException;
    public boolean removeCustomer(String customerID) throws DataNotFoundException, CannotRemoveDataException;
    public List<Customer> sortByCustomerName(SortUtil.SortOrder sortOrder,List<Customer> currentCustomers);
    public List<Customer> filterByCustomerName(String filter,List<Customer> currentCustomers);

}
