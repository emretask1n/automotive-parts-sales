package com.emretaskin.itg.service.interfaces;

import com.emretaskin.itg.dto.request.CartItemRequest;
import com.emretaskin.itg.dto.response.CartItemResponse;
import com.emretaskin.itg.entity.CartItem;

import java.util.List;

public interface CartItemService {
    void addItemToCart(Long userId, Long productId, int quantity);

    void deleteItemFromCartById(Long productId);

    List<CartItemResponse> getAllCartItemsByUserId(Long userId);

    List<CartItem> findCartItemsByUserId(Long userId);
    void updateChartItemQuantity(Long cartItemId, CartItemRequest cartItemRequest);

    void clearCartItemsByUserId(Long userId);
}
