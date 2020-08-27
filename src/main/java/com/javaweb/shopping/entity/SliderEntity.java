package com.javaweb.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Slider")
public class SliderEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String name;
    @Column(length = 191)
    private String description;
    @Column(length = 191)
    private String imagePath;
    @Column(length = 191)
    private String imageName;

    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public SliderEntity(String name, String description, String imagePath, String imageName) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public SliderEntity() {
    }
}
