package com.example.ecommerce.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Customer_ID;
    private String First_Name;
    private String Last_Name;
    private int Age;
    private String Country;

    public Customer(){}

    public Customer(Long Customer_ID, String First_Name, String Last_Name, int Age, String Country){
        this.Customer_ID = Customer_ID;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Age = Age;
        this.Country = Country;

    }

    public Long getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(Long customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountry() {
        return Country;
    }
}
