package com.javaweb.shopping.dto;

public class CategoryDTO extends AbstractDTO {
    private String name;
    private String slug;
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public CategoryDTO(String name, String slug, Integer parentId) {
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
    }

    public CategoryDTO() {
    }
}
