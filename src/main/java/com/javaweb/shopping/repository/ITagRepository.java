package com.javaweb.shopping.repository;

import com.javaweb.shopping.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITagRepository extends JpaRepository<TagEntity, Integer> {
    Optional<TagEntity> findByName(String name);
}
