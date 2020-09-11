package com.javaweb.shopping.converter;

import com.javaweb.shopping.dto.ProductImageDTO;
import com.javaweb.shopping.entity.ProductEntity;
import com.javaweb.shopping.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductImageConverter {
    public ProductImageEntity toEntity(ProductImageDTO productImageDTO, ProductEntity productEntity) {
        ProductImageEntity productImageEntity = new ProductImageEntity();
        productImageEntity.setImageName(productImageDTO.getImageName());
        productImageEntity.setImagePath(productImageDTO.getImagePath());
        productImageEntity.setProduct(productEntity);
        productImageEntity.setActive(true);
        return productImageEntity;
    }

    public ProductImageDTO toDTO(ProductImageEntity productImageEntity) {
        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setImageName(productImageEntity.getImageName());
        productImageDTO.setImagePath(productImageEntity.getImagePath());
        productImageDTO.setActive(productImageEntity.getActive());
        productImageDTO.setId(productImageEntity.getId());
        return productImageDTO;
    }
}
