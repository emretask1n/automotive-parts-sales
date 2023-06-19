package com.emretaskin.itg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Blob;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private Blob image;

    public Product(String name, BigDecimal price, int quantity, Blob image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

}
