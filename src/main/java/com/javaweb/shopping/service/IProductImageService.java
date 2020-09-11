package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ProductImageEntity;

import java.util.Optional;

public interface IProductImageService {
    Optional<ProductImageEntity> findByImageName(String imageName);
    ProductImageEntity save(ProductImageEntity newProductImage);
}
