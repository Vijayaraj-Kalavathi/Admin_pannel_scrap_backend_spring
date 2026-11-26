package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.CustomerService;
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

        customer.setStatus("ACTIVE");

        return customerrepository.save(customer);
    }

    // GET ALL ACTIVE SCRAP TYPES
    @Override
    public List<Customer> getAllCustomers() {

        return customerrepository.findByStatus("ACTIVE");
    }

    // GET BY ID (ACTIVE ONLY)
    @Override
    public Customer getCustomerById(Long id) {

        return customerrepository.findByCustomerIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active ScrapType not found"));
    }

  @Override
  public Customer updateCustomerById(Long id, Customer customer){

      Customer existscrapType = customerrepository
              .findByCustomerIdAndStatus(id, "ACTIVE")
              .orElseThrow(() ->
                      new RuntimeException("Active ScrapType not found"));

      existscrapType.setCustomer_Name(customer.getCustomer_Name());
      existscrapType.setAddress(customer.getAddress());
      existscrapType.setGst_No(customer.getGst_No());
      return  customerrepository.save(existscrapType);
  }


    // SOFT DELETE
    @Override
    public Customer deleteCustomerById(Long id) {

        Customer customer = customerrepository
                .findByCustomerIdAndStatus(id, "ACTIVE")
                .orElseThrow(() ->
                        new RuntimeException("Active ScrapType not found"));

        customer.setStatus("INACTIVE");

        return customerrepository.save(customer);
    }

}