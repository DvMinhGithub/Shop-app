package com.project.shopapp.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.dto.request.ProductRequest;
import com.project.shopapp.dto.response.ApiResponse;
import com.project.shopapp.entity.Product;
import com.project.shopapp.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping
    public ApiResponse<Page<Product>> getProducts(PageRequest pageRequest) {
        return ApiResponse.<Page<Product>>builder()
                .code(HttpStatus.OK.value())
                .result(productService.getAllProducts(pageRequest))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        return ApiResponse.<Product>builder()
                .code(HttpStatus.OK.value())
                .result(productService.getProductById(id))
                .build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Product> createProduct(@ModelAttribute @Valid ProductRequest request) throws IOException {
        return ApiResponse.<Product>builder()
                .code(HttpStatus.CREATED.value())
                .result(productService.createProduct(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ApiResponse.<Product>builder()
                .code(HttpStatus.OK.value())
                .result(productService.updateProduct(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Product deleted successfully")
                .build();
    }
}
