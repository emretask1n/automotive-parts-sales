package com.emretaskin.itg.controller;

import com.emretaskin.itg.dto.request.OrderRequest;
import com.emretaskin.itg.entity.Order;
import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Order> placeOrder(Authentication authentication, @RequestBody OrderRequest orderRequest) {
        User userPrincipal = (User) authentication.getPrincipal();
        Long userId = userPrincipal.getId();

        Order order = orderService.placeOrder(userId, orderRequest);

        return ResponseEntity.ok(order);
    }

}
