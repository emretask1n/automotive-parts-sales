package com.emretaskin.itg.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {
    private Long id;
    private String productName;
    private int quantity;
    private BigDecimal totalPrice;
}
