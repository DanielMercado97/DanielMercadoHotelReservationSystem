package ServiceClasses;

import ModelClasses.Customer;

import java.util.Collection;
import java.util.HashSet;
/*
https://knowledge.udacity.com/questions/657687
 */

public class CustomerService {
    //declaration of static reference, references the class.
    private static CustomerService customerService;
    Collection<Customer> setOfCustomers = new HashSet<>();

    private CustomerService(){}

    //Implemented from the Knowledge question board regarding using static references.
    public static CustomerService getInstance(){
        //if an object in customerService is null, it'll print a line saying the object is null and will instantiate an object of the class
        if(null==customerService){
            //System.out.println("Customer Service: null! Instantiating an object of the class.");
            customerService = new CustomerService();
        }
        //otherwise? we will just return the reference of the class itself (at least that's what it should do).
        return customerService;
    }
    //function to add customers to the setOfCustomer set.
    public void addCustomer(String email, String firstName, String lastName){
        Customer newCustomer = new Customer(firstName, lastName, email);
        setOfCustomers.add(newCustomer);

    }

    public Customer getCustomer(String customerEmail){
        //foreach loop retrieving email address from the hashset
        for (Customer eachCustomer: setOfCustomers) {
            //if the calls to the getEmail getter within the Customer class equals the email set within the setOfCustomers set, return it
          if(eachCustomer.getEmail().equals(customerEmail)){
              return eachCustomer;
          }
        }
        return null;
    }

    public Collection<Customer> getAllCustomer(){
        return setOfCustomers;
    }
}
