package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController{
    private final OrderService orderservice;

    public OrderController(OrderService orderservice){
        this.orderservice = orderservice;
    }

    @PostMapping
    public Order createOrder(Order order){
        return orderservice.createOrder(order);
    }




}
