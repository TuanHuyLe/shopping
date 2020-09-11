package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.ProductEntity;
import com.javaweb.shopping.repository.ICategoryRepository;
import com.javaweb.shopping.repository.IProductRepository;
import com.javaweb.shopping.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> findBySlugContaining(String slug, Pageable pageable) {
        return productRepository.findBySlugContaining(slug, pageable);
    }

    @Override
    public ProductEntity save(ProductEntity newProduct) {
        if (newProduct.getId() == null) {
            Optional<ProductEntity> productEntity = productRepository.findByName(newProduct.getName());
            if (productEntity.isPresent()) {
                return null;
            }
        }
        return productRepository.save(newProduct);
    }

    @Override
    public Optional<ProductEntity> remove(Integer id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            productEntity.get().setActive(false);
            return Optional.of(productRepository.save(productEntity.get()));
        }
        return Optional.empty();
    }
}
