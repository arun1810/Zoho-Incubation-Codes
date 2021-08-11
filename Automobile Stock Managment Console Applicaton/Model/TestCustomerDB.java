package Model;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import Model.BaseInterface.BaseCustomerDatabase;
import POJO.Customer;
import Utilities.JsonDB;
import Utilities.SortUtil.SortOrder;

public class TestCustomerDB implements BaseCustomerDatabase {
    private List<Customer> customers;
    private File CustomerDbFile;
    private JsonDB jsonDB;
    
    public TestCustomerDB(File customerDBFile){
        jsonDB = JsonDB.getInstance();
        this.CustomerDbFile = customerDBFile; 
    }
       

    @Override
    public List<Customer> getAllCustomerData() {
        customers = jsonDB.getAllDataFromFile(CustomerDbFile, Customer.class);
        return customers;
    }

    @Override
    public Customer getCustomerData(String customerID) throws DataNotFoundException  {
        if(customers==null) customers = getAllCustomerData();
        
        Customer customer = customers.stream()
                                    .filter(customer1->customer1.getCustomerId().equals(customerID))
                                    .findFirst()
                                    .orElse(null);
        if(customer==null) throw new DataNotFoundException("Given customerID cannot be found\n");
       return customer;
    }
    @Override
    public void addCustomerData(Customer customer) throws CannotAddDataException  {
        customers = jsonDB.writeDataToDataBase(customer,CustomerDbFile,Customer.class);
    }

    @Override
    public List<Customer> sortByCustomerName(SortOrder sortOrder, List<Customer> currentCustomers) {
        switch(sortOrder){
            case Ascending:
                            
                            currentCustomers.sort( (customer1,customer2) ->(customer1.getName().compareTo(customer2.getName())));
                            return  currentCustomers;
            
            case Descending:
                           currentCustomers.sort((customer1,customer2)->(int)(customer2.getName().compareTo(customer1.getName())));
                           return currentCustomers;
        }

        return null;
    }

    @Override
    public List<Customer> filterByCustomerName(String customerName, List<Customer> currentCustomers) {
        String filter="(.*)"+customerName+"(.*)";
        return currentCustomers.stream().filter(customer->customer.getName().matches(filter)).collect(Collectors.toList());
    }

    @Override
    public void removeCustomer(String customerID) throws DataNotFoundException,CannotRemoveDataException {
        if(customers==null) customers = getAllCustomerData();
        customers = jsonDB.removeDataFromDataBase(getCustomerData(customerID), CustomerDbFile, Customer.class);
    }
}
