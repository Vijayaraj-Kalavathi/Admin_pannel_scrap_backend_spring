package com.example.ecommerce.Model.Dashboard;


import java.util.List;

public record ScrapDashboardResponse(
        Double totalQuantity,
        List<ScrapTypeSummary> totalScrapCategories,
        Long activeExporters,
        List<CustomerDispatch> totalDispatch,
        List<MonthlyDispatch> monthlyDispatch
) {}
