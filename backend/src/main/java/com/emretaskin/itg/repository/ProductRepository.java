package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
