package com.javaweb.shopping.converter;

import com.javaweb.shopping.dto.ProductDTO;
import com.javaweb.shopping.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductEntity toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        if (productDTO.getId() != null) {
            productEntity.setId(productDTO.getId());
        }
        productEntity.setName(productDTO.getName());
        productEntity.setContent(productDTO.getContent());
        productEntity.setFeatureImageName(productDTO.getFeatureImageName());
        productEntity.setFeatureImagePath(productDTO.getFeatureImagePath());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setActive(true);
        return productEntity;
    }

    public ProductDTO toDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setActive(productEntity.getActive());
        productDTO.setName(productEntity.getName());
        productDTO.setContent(productEntity.getContent());
        productDTO.setFeatureImageName(productEntity.getFeatureImageName());
        productDTO.setFeatureImagePath(productEntity.getFeatureImagePath());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setCategoryId(productEntity.getCategory().getId());
        return productDTO;
    }
}
