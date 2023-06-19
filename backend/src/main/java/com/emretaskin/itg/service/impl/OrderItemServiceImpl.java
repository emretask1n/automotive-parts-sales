package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.entity.OrderItem;
import com.emretaskin.itg.repository.OrderItemRepository;
import com.emretaskin.itg.service.interfaces.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public void saveOrderItemsToOrder(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}
