package com.glory.orderservice.service;

import com.glory.orderservice.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(String authHeader);
}
