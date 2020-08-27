package com.javaweb.shopping.enitty;

import javax.persistence.*;

@Entity
@Table(name = "ProductImage")
public class ProductImageEntity extends BaseEntity<UserEntity> {
    @Column(length = 191)
    private String imagePath;
    @Column(length = 191)
    private String imageName;

    //many to one with product
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    //getter and setter
    public void setProduct(ProductEntity product) {
        this.product = product;
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

    public ProductImageEntity(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public ProductImageEntity() {
    }
}
