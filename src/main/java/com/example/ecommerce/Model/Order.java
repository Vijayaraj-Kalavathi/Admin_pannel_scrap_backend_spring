package com.example.ecommerce.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Order_Id;
    private String Item;
    private float Amount;

    // customer_id will be the Foreign Key
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(){}

    public Order(Long Order_Id, Customer customer, String Item, float Amount){
           this.Order_Id = Order_Id;
           this.customer = customer;
           this.Item = Item;
           this.Amount = Amount;
    }

    public void setOrder_Id(Long Order_Id){
        this.Order_Id = Order_Id;
    }

    public Long getOrder_Id() {
        return Order_Id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }
}
