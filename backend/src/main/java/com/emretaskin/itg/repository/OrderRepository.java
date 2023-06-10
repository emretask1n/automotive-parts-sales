package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
