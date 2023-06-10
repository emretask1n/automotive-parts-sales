package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.CartItem;
import com.emretaskin.itg.entity.Product;
import com.emretaskin.itg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    List<CartItem> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
