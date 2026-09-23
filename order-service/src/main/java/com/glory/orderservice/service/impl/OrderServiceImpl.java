package com.glory.orderservice.service.impl;

import com.glory.orderservice.client.CartClient;
import com.glory.orderservice.client.ProductClient;
import com.glory.orderservice.dto.response.CartItemInfo;
import com.glory.orderservice.dto.response.OrderItemResponse;
import com.glory.orderservice.dto.response.OrderResponse;
import com.glory.orderservice.dto.response.ProductInfo;
import com.glory.orderservice.entity.Order;
import com.glory.orderservice.entity.OrderItem;
import com.glory.orderservice.entity.OrderStatus;
import com.glory.orderservice.repository.OrderItemRepository;
import com.glory.orderservice.repository.OrderRepository;
import com.glory.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient  productClient;
    private final CartClient cartClient;

    private OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProductId(item.getProductId());
            itemResponse.setProductName(item.getProductName());
            itemResponse.setPrice(item.getPrice());
            itemResponse.setQuantity(item.getQuantity());

            itemResponses.add(itemResponse);
        }
        response.setItems(itemResponses);

        return response;
    }

    @Override
    public OrderResponse placeOrder(String authHeader) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<CartItemInfo>  cartItems = cartClient.getCartItems(authHeader);

        if(cartItems.isEmpty()){
            throw new IllegalArgumentException("Cannot checkout an empty cart");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        Order order = new Order();
        order.setUserId(UUID.fromString(userId));
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        for(CartItemInfo cartItem :  cartItems){
            ProductInfo product = productClient.getProductById(cartItem.getProductId());

            if(product.getStockQuantity() < cartItem.getQuantity()){
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            BigDecimal lineTotal = product.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order saved = orderRepository.save(order);

        return toResponse(saved);
    }
}
