package com.javaweb.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tag")
public class TagEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String name;

    //many to many with product
    @ManyToMany(mappedBy = "tags")
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

    public TagEntity(String name) {
        this.name = name;
    }

    public TagEntity() {
    }
}
