package com.example.ecommerce.Model.ScrapModel;

import java.time.LocalDate;

public class ScrapItemAll {
    private Long user_id;
    private LocalDate date;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
