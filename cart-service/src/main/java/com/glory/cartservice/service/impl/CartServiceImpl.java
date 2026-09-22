package com.glory.cartservice.service.impl;

import com.glory.cartservice.client.ProductClient;
import com.glory.cartservice.dto.response.CartItemResponse;
import com.glory.cartservice.dto.response.ProductInfo;
import com.glory.cartservice.dto.request.AddToCartRequest;
import com.glory.cartservice.entity.CartItem;
import com.glory.cartservice.repository.CartItemRepository;
import com.glory.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductClient productClient;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemResponse addToCart(AddToCartRequest request) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        ProductInfo product = productClient.getProductById(request.getProductId());

        Optional<CartItem> existingItem = cartItemRepository.findByUserIdAndProductId(
                UUID.fromString(userId), product.getId());

        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setUserId(UUID.fromString(userId));
            cartItem.setProductId(product.getId());
            cartItem.setQuantity(request.getQuantity());
        }

        CartItem saved = cartItemRepository.save(cartItem);

        CartItemResponse response = new CartItemResponse();
        response.setId(saved.getId());
        response.setPrice(product.getPrice());
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setQuantity(saved.getQuantity());

        return response;
    }

    @Override
    public List<CartItemResponse> getCartItems() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<CartItem> items = cartItemRepository.findByUserId(UUID.fromString(userId));

        List<CartItemResponse> responses = new ArrayList<>();

        for(CartItem item : items) {
            ProductInfo product = productClient.getProductById(item.getProductId());

            CartItemResponse response = new CartItemResponse();

            response.setId(item.getId());
            response.setProductId(product.getId());
            response.setPrice(product.getPrice());
            response.setQuantity(item.getQuantity());

            responses.add(response);
        }

        return responses;
    }
}
