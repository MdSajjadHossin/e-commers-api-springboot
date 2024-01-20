package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.Address;
import com.springboot.ecommers.entity.Order;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.OrderException;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order shippingOrder(Long orderId) throws OrderException;
    public Order delivaredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public List<Order>getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}
