package Service;

import Entities.Customer;
import Repositories.CustomerRepository;

public class CustomerService {
    public Customer createCustomer(UUID customerId, String firstName, String lastName, String email) {
        Customer customer = new Customer(customerId, firstName, lastName, email);
        CustomerRepository.save(customer);
        System.out.println("Customer created: " + firstName + " " + lastName);
        return customer;
    }
}
