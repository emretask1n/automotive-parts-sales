package com.emretaskin.itg.controller;

import com.emretaskin.itg.dto.request.ProductRequest;
import com.emretaskin.itg.dto.response.ProductResponse;
import com.emretaskin.itg.enums.SortType;
import com.emretaskin.itg.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Sort all products")
    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> sortAllProducts(@RequestParam("sort") String sortType) {
        return ResponseEntity.ok(productService.sortAllProducts(SortType.valueOf(sortType)));
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable Long productId, @RequestBody ProductRequest updatedProduct){
        return ResponseEntity.ok(productService.updateProductById(productId,updatedProduct));
    }

    @Operation(summary = "Save a new product")
    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @Operation(summary = "Filter products by price range")
    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> filterProductsByPriceRange(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
        List<ProductResponse> filteredProducts = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(filteredProducts);
    }
}

