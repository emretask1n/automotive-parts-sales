package com.emretaskin.itg.service.interfaces;

import com.emretaskin.itg.dto.request.ProductRequest;
import com.emretaskin.itg.dto.response.ProductResponse;
import com.emretaskin.itg.entity.Product;
import com.emretaskin.itg.enums.SortType;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    List<ProductResponse> sortAllProducts(SortType sortType);

    void deleteProductById(Long productId);

    ProductResponse updateProductById(Long productId, ProductRequest updatedProduct);

    ProductResponse saveProduct(ProductRequest productRequest);

    Product getProductById(Long id);
}
