package com.emretaskin.itg.controller;

import com.emretaskin.itg.dto.request.CartItemRequest;
import com.emretaskin.itg.dto.response.CartItemResponse;
import com.emretaskin.itg.service.interfaces.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<CartItemResponse>> listAllCartItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartItemService.getAllCartItemsByUserId(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long userId,  @RequestBody CartItemRequest cartItemRequest) {
        cartItemService.addItemToCart(userId, cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItemFromCartById(@PathVariable Long id) {
        cartItemService.deleteItemFromCartById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateChartItemQuantity(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest) {
        cartItemService.updateChartItemQuantity(id,cartItemRequest);
        return ResponseEntity.noContent().build();
    }

}