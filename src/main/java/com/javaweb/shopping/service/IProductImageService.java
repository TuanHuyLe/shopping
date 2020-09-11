package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ProductImageEntity;

import java.util.List;
import java.util.Optional;

public interface IProductImageService {
    Optional<ProductImageEntity> findByImageName(String imageName);
    List<ProductImageEntity> save(List<ProductImageEntity> newProductImages);
}
