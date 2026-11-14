package com.example.ecommerce.Model;

import java.time.LocalDate;

public class ScrapItemFilter {
    private Long users_id;
    private LocalDate date;
    private Long scraptype_id;
    private Long customers_id;


    public Long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Long users_id) {
        this.users_id = users_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getScraptype_id() {
        return scraptype_id;
    }

    public void setScraptype_id(Long scraptype_id) {
        this.scraptype_id = scraptype_id;
    }

    public Long getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(Long customers_id) {
        this.customers_id = customers_id;
    }

}
