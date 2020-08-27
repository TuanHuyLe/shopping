package com.javaweb.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class MenuEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String name;
    @Column(length = 191)
    private String slug;
    @Column(columnDefinition = "integer default 0")
    private Integer parentId;

    //getter and setter
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

    public MenuEntity(String name, String slug, Integer parentId) {
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
    }

    public MenuEntity() {
    }
}
