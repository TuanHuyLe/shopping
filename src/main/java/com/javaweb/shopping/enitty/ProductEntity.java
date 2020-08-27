package com.javaweb.shopping.enitty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
public class ProductEntity extends BaseEntity<UserEntity> {
    @Column(length = 191)
    private String name;
    @Column
    private Long price;
    @Column(length = 191)
    private String featureImagePath;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(length = 191)
    private String featureImageName;

    //many to many with tag
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ProductTag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags = new ArrayList<>();

    //one to many with product image
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductImageEntity> productImages = new ArrayList<>();

    //one to many with user
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    //one to many with category
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    //getter and setter
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setProductImages(List<ProductImageEntity> productImages) {
        this.productImages = productImages;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
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

    public String getFeatureImagePath() {
        return featureImagePath;
    }

    public void setFeatureImagePath(String featureImagePath) {
        this.featureImagePath = featureImagePath;
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

    public ProductEntity(String name, Long price, String featureImagePath, String content, String featureImageName) {
        this.name = name;
        this.price = price;
        this.featureImagePath = featureImagePath;
        this.content = content;
        this.featureImageName = featureImageName;
    }

    public ProductEntity() {
    }
}