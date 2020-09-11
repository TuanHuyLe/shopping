package com.javaweb.shopping.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends AbstractDTO {
    private String content;
    private String featureImageName;
    private String featureImagePath;
    private String name;
    private String slug;
    private Long price;
    private Integer categoryId;
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeatureImageName() {
        return featureImageName;
    }

    public void setFeatureImageName(String featureImageName) {
        this.featureImageName = featureImageName;
    }

    public String getFeatureImagePath() {
        return featureImagePath;
    }

    public void setFeatureImagePath(String featureImagePath) {
        this.featureImagePath = featureImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductDTO(String content, String featureImageName, String featureImagePath, String name, Long price) {
        this.content = content;
        this.featureImageName = featureImageName;
        this.featureImagePath = featureImagePath;
        this.name = name;
        this.price = price;
    }

    public ProductDTO() {
        tags = new ArrayList<>();
    }
}
