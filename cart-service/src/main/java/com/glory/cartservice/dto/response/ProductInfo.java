package com.glory.cartservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductInfo {

    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}
