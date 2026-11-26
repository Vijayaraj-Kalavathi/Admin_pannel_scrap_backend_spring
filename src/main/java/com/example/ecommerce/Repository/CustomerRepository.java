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
    List<Customer> findByStatus(String status);

    @Query("SELECT c FROM Customer c WHERE c.customer_id = :customerId AND c.status = :status")
    Optional<Customer> findByCustomerIdAndStatus(
            @Param("customerId") Long customerId,
            @Param("status") String status
    );
}
