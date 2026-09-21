package com.glory.productcatalogservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    @NotNull
    private String name;

    private String description;
}
