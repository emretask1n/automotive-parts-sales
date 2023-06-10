package com.emretaskin.itg.dto.request;

import com.emretaskin.itg.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Blob;

@Data
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Blob image;

    public void updateProduct(Product product) {
        if (this.name != null) {
            product.setName(this.name);
        }
        if (this.image != null) {
            product.setImage(this.image);
        }
        if (this.price != null) {
            product.setPrice(this.price);
        }
        if (this.quantity != null) {
            product.setQuantity(this.quantity);
        }
    }
}

