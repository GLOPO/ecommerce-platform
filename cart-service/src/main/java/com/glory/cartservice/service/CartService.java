package com.glory.cartservice.service;

import com.glory.cartservice.dto.response.CartItemResponse;
import com.glory.cartservice.dto.request.AddToCartRequest;

public interface CartService {
    CartItemResponse addToCart(AddToCartRequest request);
}
