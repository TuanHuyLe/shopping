package com.javaweb.shopping.converter;

import com.javaweb.shopping.dto.ProductDTO;
import com.javaweb.shopping.dto.ProductImageDTO;
import com.javaweb.shopping.entity.ProductEntity;
import com.javaweb.shopping.utils.SlugifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {
    @Autowired
    private SlugifyUtils slugifyUtils;
    @Autowired
    private ProductImageConverter productImageConverter;

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
        productEntity.setSlug(slugifyUtils.slugify(productDTO.getName()));
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
        productDTO.setSlug(productEntity.getSlug());
        productDTO.setCategoryId(productEntity.getCategory().getId());
        // add list tag
        List<String> tags = new ArrayList<>();
        productEntity.getTags().forEach(tag -> tags.add(tag.getName()));
        productDTO.setTags(tags);
        // add list product image
        List<ProductImageDTO> productImages = new ArrayList<>();
        productEntity.getProductImages().forEach(image -> productImages.add(productImageConverter.toDTO(image)));
        productDTO.setProductImages(productImages);
        return productDTO;
    }
}
