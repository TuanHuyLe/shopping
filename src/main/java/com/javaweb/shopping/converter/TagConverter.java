package com.javaweb.shopping.converter;

import com.javaweb.shopping.dto.TagDTO;
import com.javaweb.shopping.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {
    public TagEntity toEntity(TagDTO tagDTO){
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagDTO.getName());
        tagEntity.setActive(true);
        return tagEntity;
    }

    public TagDTO toDTO(TagEntity tagEntity){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(tagEntity.getName());
        return tagDTO;
    }
}
