package com.javaweb.shopping.dto;

public class ProductImageDTO extends AbstractDTO {
    private String imagePath;
    private String imageName;

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

    public ProductImageDTO(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public ProductImageDTO() {
    }
}
