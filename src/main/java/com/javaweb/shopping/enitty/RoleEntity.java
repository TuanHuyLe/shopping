package com.javaweb.shopping.enitty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Role")
public class RoleEntity extends BaseEntity<UserEntity> {
    @Column(length = 191)
    private String name;
    @Column(length = 191)
    private String displayName;

    //many to many with user
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();

    //many to many with permission
    @ManyToMany(mappedBy = "roles")
    private List<PermissionEntity> permission = new ArrayList<>();

    //getter and setter
    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public void setPermission(List<PermissionEntity> permission) {
        this.permission = permission;
    }

    public void setPosts(List<UserEntity> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public RoleEntity(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public RoleEntity() {
    }
}
