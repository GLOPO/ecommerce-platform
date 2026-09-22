package com.glory.cartservice.service;

import com.glory.cartservice.dto.response.CartItemResponse;
import com.glory.cartservice.dto.request.AddToCartRequest;

import java.util.List;
import java.util.UUID;

public interface CartService {
    CartItemResponse addToCart(AddToCartRequest request);

    List<CartItemResponse> getCartItems();

    void deleteFromCart(UUID cartItemId);
}
