package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.dto.request.OrderRequest;
import com.emretaskin.itg.dto.response.CartItemResponse;
import com.emretaskin.itg.entity.CartItem;
import com.emretaskin.itg.entity.Order;
import com.emretaskin.itg.entity.OrderItem;
import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.enums.OrderStatus;
import com.emretaskin.itg.service.interfaces.CartItemService;
import com.emretaskin.itg.service.interfaces.OrderService;
import com.emretaskin.itg.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final EmailService emailService;

    @Override
    public Order placeOrder(Long userId, OrderRequest orderRequest) {
        User user = userService.getUserById(userId);
        LocalDateTime orderDate = LocalDateTime.now();

        List<CartItem> cartItems = cartItemService.findCartItemsByUserId(userId);


        Order order = Order.builder()
                .user(user)
                .orderDate(orderDate)
                .shippingAddress(orderRequest.getShippingAddress())
                .build();

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                }).toList();

        cartItemService.clearCartItemsByUserId(userId);

        String userEmail = user.getEmail();
        String subject = "Yeni Sipariş Oluşturuldu";
        String body = "Sipariş bilgileri: " + order.toString();
        emailService.sendEmail(userEmail, subject, body);

        return order;
    }
}
