package com.glory.cartservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddToCartRequest {

    @NotNull
    private UUID productId;
    @NotNull
    private Integer quantity;
}
