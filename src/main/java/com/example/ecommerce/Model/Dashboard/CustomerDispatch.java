package com.example.ecommerce.Model.Dashboard;

public record CustomerDispatch(
        Long customerId,
        String customerName,
        Long dispatchCount,
        Double totalQuantity
) {}