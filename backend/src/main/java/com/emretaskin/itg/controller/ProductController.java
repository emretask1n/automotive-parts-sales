package com.emretaskin.itg.controller;

import com.emretaskin.itg.dto.request.ProductRequest;
import com.emretaskin.itg.dto.response.ProductResponse;
import com.emretaskin.itg.enums.SortType;
import com.emretaskin.itg.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> sortAllProducts(@RequestParam("sort") String sortType) {
        return ResponseEntity.ok(productService.sortAllProducts(SortType.valueOf(sortType)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable Long productId, @RequestBody ProductRequest updatedProduct){
        return ResponseEntity.ok(productService.updateProductById(productId,updatedProduct));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }
}
