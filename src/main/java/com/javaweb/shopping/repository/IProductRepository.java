package com.javaweb.shopping.repository;

import com.javaweb.shopping.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByName(String name);
}
