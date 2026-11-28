package com.example.ecommerce.Model.Dashboard;

public record MonthlyDispatch(
        String month,
        Long dispatchCount,
        Double totalQuantity
) {}