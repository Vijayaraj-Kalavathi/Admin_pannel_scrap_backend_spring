package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Repository.OrderRepository;
import com.example.ecommerce.Service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderrepository;

    public OrderServiceImp(OrderRepository orderrepository){
        this.orderrepository = orderrepository;
    }

    public Order createOrder(Order order){
        return orderrepository.save(order);
    }
}
