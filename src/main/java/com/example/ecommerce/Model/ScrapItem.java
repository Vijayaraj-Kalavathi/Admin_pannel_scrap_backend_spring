package com.example.ecommerce.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "scrapitem")
public class ScrapItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scraptype_id", referencedColumnName = "id")
    private ScrapType scrapType;

    private float quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customers_id", referencedColumnName = "customer_id")
    private Customer customer;

    public ScrapItem(){}

     public ScrapItem(Long id, LocalDate date, ScrapType scrapType, float quantity, Customer customer){
        this.id = id;
        this.date = date;
        this.scrapType = scrapType;
        this.quantity = quantity;
        this.customer = customer;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
         return date;
     }

     public void setDate(LocalDate date) {
         this.date = date;
     }

     public ScrapType getScrapType() {
         return scrapType;
     }

     public void setScrapType(ScrapType scrapType) {
         this.scrapType = scrapType;
     }

     public float getQuantity() {
         return quantity;
     }

     public void setQuantity(float quantity) {
         this.quantity = quantity;
     }

     public Customer getCustomer() {
         return customer;
     }

     public void setCustomer(Customer customer) {
         this.customer = customer;
     }
 }
