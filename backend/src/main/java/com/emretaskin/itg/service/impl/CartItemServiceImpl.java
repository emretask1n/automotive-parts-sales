package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.dto.request.CartItemRequest;
import com.emretaskin.itg.dto.response.CartItemResponse;
import com.emretaskin.itg.entity.CartItem;
import com.emretaskin.itg.entity.Product;
import com.emretaskin.itg.entity.User;
import com.emretaskin.itg.repository.CartItemRepository;
import com.emretaskin.itg.service.interfaces.CartItemService;
import com.emretaskin.itg.service.interfaces.ProductService;
import com.emretaskin.itg.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    @Transactional
    public void addItemToCart(Long userId, Long productId, int quantity) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(quantity)
                            .build();
            cartItemRepository.save(newCartItem);
        }
        productService.decreaseProductQuantity(productId, quantity);
    }

    @Override
    @Transactional
    public void deleteItemFromCartById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItemResponse> getAllCartItemsByUserId(Long userId) {
        List<CartItem> cartItems = findCartItemsByUserId(userId);
        return mapToCartItemResponses(cartItems);
    }

    @Override
    public List<CartItem> findCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public void updateChartItemQuantity(Long cartItemId, CartItemRequest cartItemRequest) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found with ID: " + cartItemId));

        cartItem.setQuantity(cartItemRequest.getQuantity());

        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void clearCartItemsByUserId(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    private List<CartItemResponse> mapToCartItemResponses(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::convertToCartItemResponseWithTotalPrice)
                .collect(Collectors.toList());
    }

    private CartItemResponse convertToCartItemResponseWithTotalPrice(CartItem cartItem) {
        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productName(cartItem.getProduct().getName())
                .quantity(cartItem.getQuantity())
                .totalPrice(totalPrice)
                .build();
    }

}
