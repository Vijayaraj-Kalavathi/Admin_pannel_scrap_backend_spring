package com.example.ecommerce.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String customer_name;
    private String address;
    private String gst_no;

    public Customer(){}

    public Customer(Long customer_id, String customer_name, String address, String gst_no){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.gst_no = gst_no;
    }

    public Long getCustomer_ID() {
        return customer_id;
    }

    public void setCustomer_ID(Long customer_ID) {
        customer_id = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_name;
    }

    public void setCustomer_Name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGst_No() {
        return gst_no;
    }

    public void setGst_No(String gst_no) {
        this.gst_no = gst_no;
    }
}
