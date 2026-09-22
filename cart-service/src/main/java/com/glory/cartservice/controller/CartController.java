package com.glory.cartservice.controller;

import com.glory.cartservice.dto.request.AddToCartRequest;
import com.glory.cartservice.dto.response.CartItemResponse;
import com.glory.cartservice.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartItemResponse> addItemToCart(AddToCartRequest request) {
        CartItemResponse response = cartService.addToCart(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getAllCartItems() {
        List<CartItemResponse> response = cartService.getCartItems();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteFromCart(@PathVariable UUID id) {
        cartService.deleteFromCart(id);

        return ResponseEntity.noContent().build();
    }
}
