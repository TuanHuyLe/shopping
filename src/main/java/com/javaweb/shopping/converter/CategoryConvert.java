package com.javaweb.shopping.converter;

import com.github.slugify.Slugify;
import com.javaweb.shopping.dto.CategoryDTO;
import com.javaweb.shopping.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvert {
    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Slugify slg = new Slugify();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setSlug(slg.slugify(categoryDTO.getName()));
        categoryEntity.setParentId(categoryDTO.getParentId());
        return categoryEntity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setSlug(categoryEntity.getSlug());
        categoryDTO.setParentId(categoryEntity.getParentId());
        return categoryDTO;
    }
}
