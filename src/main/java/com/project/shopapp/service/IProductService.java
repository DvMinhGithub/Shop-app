package com.project.shopapp.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.shopapp.dto.request.ProductRequest;
import com.project.shopapp.entity.Product;

public interface IProductService {
    Product createProduct(ProductRequest request) throws IOException;

    Product getProductById(Long id);

    Page<Product> getAllProducts(PageRequest pageRequest);

    Product updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
