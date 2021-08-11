package com.aidan.service;

import com.aidan.pojo.Cart;
import com.aidan.pojo.Order;

public interface OrderService {
    public Order createOrder(Cart cart, Integer userId);
}
