package com.emretaskin.itg.service.interfaces;

import com.emretaskin.itg.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    void saveOrderItemsToOrder(List<OrderItem> orderItems);
}
