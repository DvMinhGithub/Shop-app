package com.project.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.dto.request.ProductRequest;
import com.project.shopapp.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "thumbnail", source = "thumbnail.originalFilename")
    Product toProduct(ProductRequest request, MultipartFile thumbnail);
}
