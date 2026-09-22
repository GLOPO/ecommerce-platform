package com.glory.orderservice.client;

import com.glory.orderservice.dto.response.CartItemInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CartClient {
    private final RestClient restClient;

    public CartClient(@Value("${cart-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<CartItemInfo> getCartItems(String bearerToken) {
        return restClient.get()
                .uri("api/cart")
                .header("Authorization", bearerToken)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CartItemInfo>>() {});
    }
}
