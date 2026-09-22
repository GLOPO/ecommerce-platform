package com.glory.orderservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CartItemInfo {

    private UUID id;
    private UUID productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
