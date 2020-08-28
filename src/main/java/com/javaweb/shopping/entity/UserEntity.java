package com.javaweb.shopping.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "is_active=1")
@Table(name = "User")
public class UserEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String name;
    @Column(name = "email", length = 191)
    private String username;
    @Column(length = 191)
    private String password;
    @Column(columnDefinition = "integer default 1")
    private Integer status;

    //many to many with role
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "UserRole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    //one to many with product
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductEntity> products = new ArrayList<>();

    //getter and setter
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {
    }
}
