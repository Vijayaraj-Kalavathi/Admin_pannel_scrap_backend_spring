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


  public Customer createCustomers(Customer customer){
       return customerrepository.save(customer);
  }

  @Override
  public List<Customer> getAllCustomers(){
      return customerrepository.findAll();
  }

  @Override
  public Customer getCustomerById(Long id){
      return customerrepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
  }

  @Override
  public Customer updateCustomerById(Long id, Customer customer){
      Customer existCustomer = customerrepository.findById(id).orElseThrow(()-> new RuntimeException("Customer not found"));
      existCustomer.setCustomer_Name(customer.getCustomer_Name());
      existCustomer.setAddress(customer.getAddress());
      existCustomer.setGst_No(customer.getGst_No());
      return  customerrepository.save(existCustomer);
  }

}