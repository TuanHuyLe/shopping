package com.javaweb.shopping.dto;

public class TagDTO extends AbstractDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public TagDTO() {
    }
}
