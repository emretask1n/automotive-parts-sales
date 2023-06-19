package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
