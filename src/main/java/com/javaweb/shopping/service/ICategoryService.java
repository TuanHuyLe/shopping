package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Optional<CategoryEntity> findById(Integer id);

    List<CategoryEntity> findAll();

    CategoryEntity save(CategoryEntity newCategory);

    Optional<CategoryEntity> remove(Integer id);
}
