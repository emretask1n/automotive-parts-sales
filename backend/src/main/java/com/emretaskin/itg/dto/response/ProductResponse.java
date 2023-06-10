package com.emretaskin.itg.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Blob;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private Blob image;
}
