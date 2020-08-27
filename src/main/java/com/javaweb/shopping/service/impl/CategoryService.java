package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.CategoryEntity;
import com.javaweb.shopping.repository.ICategoryRepository;
import com.javaweb.shopping.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity save(CategoryEntity newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public void remove(CategoryEntity deleteCategory) {

    }
}
