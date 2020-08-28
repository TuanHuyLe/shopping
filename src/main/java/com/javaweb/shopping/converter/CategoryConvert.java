package com.javaweb.shopping.converter;

import com.javaweb.shopping.dto.CategoryDTO;
import com.javaweb.shopping.entity.CategoryEntity;
import com.javaweb.shopping.utils.SlugifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvert {
    @Autowired
    private SlugifyUtils slugify;

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryDTO.getId() != null) {
            categoryEntity.setId(categoryDTO.getId());
        }
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setSlug(slugify.slugify(categoryDTO.getName()));
        categoryEntity.setParentId(categoryDTO.getParentId());
        categoryEntity.setActive(true);
        return categoryEntity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setSlug(categoryEntity.getSlug());
        categoryDTO.setActive(categoryEntity.getActive());
        categoryDTO.setParentId(categoryEntity.getParentId());
        return categoryDTO;
    }
}
