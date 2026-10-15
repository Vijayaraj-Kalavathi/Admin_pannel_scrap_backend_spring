package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Customer;

import java.util.List;

public interface CustomerService{
Customer createCustomers(Customer customer);
List<Customer> getAllCustomers();
Customer getCustomerById(Long id);
Customer updateCustomerById(Long id, Customer customer);
}
