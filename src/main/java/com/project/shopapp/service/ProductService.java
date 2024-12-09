package com.project.shopapp.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.dto.request.ProductRequest;
import com.project.shopapp.entity.Product;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.mapper.ProductMapper;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ProductService implements IProductService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    ProductMapper productMapper;
    FileStorageService fileStorageService;

    @Override
    public Product createProduct(ProductRequest request) throws IOException {
        MultipartFile file = request.getThumbnail();
        categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found"));

        Product product = productMapper.toProduct(request, file);
        if (!file.isEmpty()) {
            String fileName = fileStorageService.saveFile(file, "products/");
            product.setThumbnail(fileName);
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest request) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        productMapper.toProduct(request, null);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product =
                productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
