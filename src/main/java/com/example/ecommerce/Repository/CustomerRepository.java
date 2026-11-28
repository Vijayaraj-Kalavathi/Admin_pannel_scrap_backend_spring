package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // GET ACTIVE CUSTOMERS - LATEST FIRST
    @Query("SELECT c FROM Customer c WHERE c.status = :status ORDER BY c.customer_id DESC")
    List<Customer> findActiveCustomersLatestFirst(
            @Param("status") String status
    );

    // GET CUSTOMER BY ID
    @Query("SELECT c FROM Customer c WHERE c.customer_id = :customerId AND c.status = :status")
    Optional<Customer> findByCustomerIdAndStatus(
            @Param("customerId") Long customerId,
            @Param("status") String status
    );

    // CHECK DUPLICATE CUSTOMER NAME
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE LOWER(c.customer_name) = LOWER(:customerName)")
    boolean existsCustomerName(
            @Param("customerName") String customerName
    );
}
