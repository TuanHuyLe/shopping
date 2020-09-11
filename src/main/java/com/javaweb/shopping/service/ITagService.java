package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.TagEntity;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    Optional<TagEntity> save(TagEntity newTag);
    List<TagEntity> findAll();
    Optional<TagEntity> findByName(String name);
}
