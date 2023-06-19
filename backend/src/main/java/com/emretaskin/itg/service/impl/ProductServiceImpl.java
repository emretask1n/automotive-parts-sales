package com.emretaskin.itg.service.impl;

import com.emretaskin.itg.dto.request.ProductRequest;
import com.emretaskin.itg.dto.response.ProductResponse;
import com.emretaskin.itg.entity.Product;
import com.emretaskin.itg.enums.SortType;
import com.emretaskin.itg.repository.ProductRepository;
import com.emretaskin.itg.service.interfaces.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        return getProductResponses(productRepository.findAll());
    }

    @Override
    public List<ProductResponse> sortAllProducts(SortType sortType) {
        return getSortedProducts(sortType);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        productRepository.deleteById(getProductById(productId).getId());
    }

    @Override
    @Transactional
    public ProductResponse updateProductById(Long productId, ProductRequest updatedProduct) {
        Product product = getProductById(productId);
        updatedProduct.updateProduct(product);
        productRepository.save(product);
        return convertToProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .image(productRequest.getImage())
                .build();

        Product savedProduct = productRepository.save(product);

        return convertToProductResponse(savedProduct);
    }

    private List<ProductResponse> getProductResponses(List<Product> products) {
        return products.stream()
                .map(this::convertToProductResponse)
                .collect(Collectors.toList());
    }

    private List<ProductResponse> getSortedProducts(SortType sortType) {
        Sort sort = getSortBySortType(sortType);
        List<Product> products = productRepository.findAll(sort);
        return getProductResponses(products);
    }

    private Sort getSortBySortType(SortType sortType) {
        return switch (sortType) {
            case ASCENDING -> Sort.by(Sort.Direction.ASC, "price");
            case DESCENDING -> Sort.by(Sort.Direction.DESC, "price");
            case ALPHABETICAL -> Sort.by(Sort.Direction.ASC, "name");
            case REVERSE -> Sort.by(Sort.Direction.DESC, "name");
        };
    }

    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .image(product.getImage())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }

    @Override
    public List<ProductResponse> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return getProductResponses(productRepository.findByPriceBetween(minPrice, maxPrice));
    }

    @Override
    public void decreaseProductQuantity(Long productId, int quantity) {
        Product product = getProductById(productId);
        int currentQuantity = product.getQuantity();
        int updatedQuantity = currentQuantity - quantity;

        if (updatedQuantity < 0) {
            throw new IllegalArgumentException("Not enough quantity available for the product.");
        }

        product.setQuantity(updatedQuantity);
        productRepository.save(product);
    }

}
