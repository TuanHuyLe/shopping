package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.ProductImageEntity;
import com.javaweb.shopping.repository.IProductImageRepository;
import com.javaweb.shopping.service.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService implements IProductImageService {
    @Autowired
    private IProductImageRepository productImageRepository;

    @Override
    public Optional<ProductImageEntity> findByImageName(String imageName) {
        return productImageRepository.findByImageName(imageName);
    }

    @Override
    public List<ProductImageEntity> save(List<ProductImageEntity> newProductImages) {
        List<ProductImageEntity> productImageDTOs = new ArrayList<>();
        newProductImages.forEach(pi -> productImageDTOs.add(productImageRepository.save(pi)));
        return productImageDTOs;
    }

}
