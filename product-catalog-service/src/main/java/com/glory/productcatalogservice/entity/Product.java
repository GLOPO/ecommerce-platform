package com.glory.productcatalogservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    private Integer stockQuantity;

    private String description;

    @NotNull
    private BigDecimal price;



    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
