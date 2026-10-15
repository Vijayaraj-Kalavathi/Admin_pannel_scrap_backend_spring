package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.Customer;
import com.example.ecommerce.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerservice;

    public CustomerController(CustomerService customerservice) {
        this.customerservice = customerservice;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> createCustomers(@RequestBody Customer customer) {
        Customer createCustomer = customerservice.createCustomers(customer);

        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Customer Created Successfully");
        response.put("data", createCustomer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerservice.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerservice.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomerById(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updateCustomer = customerservice.updateCustomerById(id, customer);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer Updated Successfully");
        response.put("data",updateCustomer);
        return ResponseEntity.ok(response);

    }


}
