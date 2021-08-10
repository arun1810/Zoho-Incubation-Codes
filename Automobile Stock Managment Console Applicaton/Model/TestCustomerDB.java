package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CustomExceptions.CannotRemoveDataException;
import CustomExceptions.DataNotFoundException;
import Model.BaseInterface.BaseCustomerDatabase;
import POJO.Customer;
import Utilities.SortUtil.SortOrder;

public class TestCustomerDB implements BaseCustomerDatabase {
    private List<Customer> customers;
    
    public TestCustomerDB(){
        customers = new ArrayList<>();
        
        customers.add(new Customer("1","name1","pass1",'M',LocalDate.now(),"123456789",true));
        customers.add(new Customer("2","name2","pass1",'F',LocalDate.now(),"223456789",false));
        customers.add(new Customer("3","name3","pass1",'M',LocalDate.now(),"323456789",true));
        customers.add(new Customer("4","name4","pass1",'M',LocalDate.now(),"423456789",true));


    }

    @Override
    public List<Customer> getAllCustomerData() {
        return customers;
    }

    @Override
    public Customer getCustomerData(String customerID) throws DataNotFoundException  {
        Customer customer = customers.stream()
                                    .filter(customer1->customer1.getCustomerId().equals(customerID))
                                    .findFirst()
                                    .orElse(null);
        if(customer==null) throw new DataNotFoundException("Given customerID cannot be found\n");
       return customer;
    }
    @Override
    public boolean addCustomerData(Customer customer)  {
        if(customers.add(customer)) return true;
        return false;
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
    public boolean removeCustomer(String customerID) throws DataNotFoundException,CannotRemoveDataException {
        if(customers.remove(getCustomerData(customerID))) return true;
        throw new CannotRemoveDataException("Given customerID cannot be removed\n");
    }
}
