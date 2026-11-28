package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

  private final CustomerRepository customerrepository;

  public CustomerServiceImp(CustomerRepository customerrepository){
      this.customerrepository = customerrepository;
  }

    // CREATE
    @Override
    public Customer createCustomers(Customer customer) {

        // Validate customer name
        if (customer.getCustomer_Name() == null ||
                customer.getCustomer_Name().isBlank()) {

            throw new IllegalArgumentException("Customer Name is required");
        }

        String customerName = customer.getCustomer_Name().trim();

        // Check duplicate
        boolean exists = customerrepository
                .existsCustomerName(customerName);

        if (exists) {
            throw new IllegalArgumentException("Customer Already Present");
        }

        customer.setCustomer_Name(customerName);
        customer.setStatus("ACTIVE");

        return customerrepository.save(customer);
    }

    // GET ALL ACTIVE CUSTOMERS - LATEST FIRST
    @Override
    public List<Customer> getAllCustomers() {

        return customerrepository.findActiveCustomersLatestFirst("ACTIVE");
    }

    // GET BY ID
    @Override
    public Customer getCustomerById(Long id) {

        return customerrepository.findByCustomerIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active Customer not found"));
    }

  @Override
  public Customer updateCustomerById(Long id, Customer customer){

      Customer existCustomer = customerrepository
              .findByCustomerIdAndStatus(id, "ACTIVE")
              .orElseThrow(() ->
                      new RuntimeException("Active Customer not found"));

      existCustomer.setCustomer_Name(customer.getCustomer_Name());
      existCustomer.setAddress(customer.getAddress());
      existCustomer.setGst_No(customer.getGst_No());
      return  customerrepository.save(existCustomer);
  }


    // SOFT DELETE
    @Override
    public Customer deleteCustomerById(Long id) {

        Customer customer = customerrepository
                .findByCustomerIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active Customer not found"));

        customer.setStatus("INACTIVE");

        return customerrepository.save(customer);
    }

}