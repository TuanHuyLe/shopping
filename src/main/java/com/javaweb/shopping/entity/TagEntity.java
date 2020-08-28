package com.javaweb.shopping.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "is_active=1")
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
