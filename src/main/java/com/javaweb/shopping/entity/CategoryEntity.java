package com.javaweb.shopping.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "is_active=1")
@Table(name = "Category")
public class CategoryEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String name;
    @Column(length = 191)
    private String slug;
    @Column(columnDefinition = "integer default 0")
    private Integer parentId;

    //many to many with product
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductEntity> products = new ArrayList<>();

    //getter and setter
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

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

    public CategoryEntity(String name, String slug, Integer parentId) {
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
    }

    public CategoryEntity() {
    }
}
