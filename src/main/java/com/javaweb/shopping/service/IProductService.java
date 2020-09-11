package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<ProductEntity> findById(Integer id);

    List<ProductEntity> findAll();

    ProductEntity save(ProductEntity newProduct);

    Optional<ProductEntity> remove(Integer id);
}
