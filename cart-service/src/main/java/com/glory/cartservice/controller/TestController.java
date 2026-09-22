package com.glory.cartservice.controller;

import com.glory.cartservice.client.ProductClient;
import com.glory.cartservice.dto.reponse.ProductInfo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final ProductClient productClient;

    @GetMapping("/{id}")
    public ResponseEntity<ProductInfo> testProductLookUp(
            @PathVariable UUID id) {
        ProductInfo product = productClient.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/whoami")
    public ResponseEntity<String> whoAmI() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(userId);
    }
}
