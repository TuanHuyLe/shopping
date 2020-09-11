package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.TagEntity;
import com.javaweb.shopping.repository.ITagRepository;
import com.javaweb.shopping.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Optional<TagEntity> save(TagEntity newTag) {
        Optional<TagEntity> tagEntity = tagRepository.findByName(newTag.getName());
        if (!tagEntity.isPresent()) {
            return Optional.of(tagRepository.save(newTag));
        }
        return Optional.empty();
    }

    @Override
    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<TagEntity> findByName(String name) {
        return tagRepository.findByName(name);
    }

}
