package com.example.ecommerce.Model.Dashboard;

public record ScrapTypeSummary(
        String scrapType,
        Long count,
        Double totalQuantity
) {}
