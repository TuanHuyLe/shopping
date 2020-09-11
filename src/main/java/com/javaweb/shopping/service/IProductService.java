package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Optional<ProductEntity> findById(Integer id);

    Page<ProductEntity> findAll(Pageable pageable);

    Page<ProductEntity> findBySlugContaining(String slug, Pageable pageable);

    ProductEntity save(ProductEntity newProduct);

    Optional<ProductEntity> remove(Integer id);
}
