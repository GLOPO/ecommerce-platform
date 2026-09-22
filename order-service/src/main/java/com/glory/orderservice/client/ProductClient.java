package com.glory.orderservice.client;

import com.glory.orderservice.dto.response.ProductInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(@Value("${product-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public ProductInfo getProductById(UUID productId) {
        return restClient.get()
                .uri("/api/products/{id}")
                .retrieve()
                .body(ProductInfo.class);
    }
}
