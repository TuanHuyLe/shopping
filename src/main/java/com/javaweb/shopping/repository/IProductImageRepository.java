package com.javaweb.shopping.repository;

import com.javaweb.shopping.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {
    Optional<ProductImageEntity> findByImageName(String imageName);
}
