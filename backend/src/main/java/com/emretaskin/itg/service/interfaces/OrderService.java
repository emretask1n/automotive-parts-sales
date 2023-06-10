package com.emretaskin.itg.service.interfaces;

import com.emretaskin.itg.dto.request.OrderRequest;
import com.emretaskin.itg.entity.Order;

public interface OrderService {
    Order placeOrder(Long userId, OrderRequest orderRequest);
}
