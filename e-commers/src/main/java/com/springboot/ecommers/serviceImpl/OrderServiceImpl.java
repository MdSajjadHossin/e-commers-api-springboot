package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.CartRepo;
import com.springboot.ecommers.entity.Address;
import com.springboot.ecommers.entity.Order;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.OrderException;
import com.springboot.ecommers.service.CartService;
import com.springboot.ecommers.service.OrderService;
import com.springboot.ecommers.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippingOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order delivaredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
