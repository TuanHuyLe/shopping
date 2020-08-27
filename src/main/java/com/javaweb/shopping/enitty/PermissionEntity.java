package com.javaweb.shopping.enitty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Permission")
public class PermissionEntity extends BaseEntity<UserEntity> {
    @Column(length = 191)
    private String name;
    @Column(length = 191)
    private String displayName;
    @Column(columnDefinition = "integer default 0")
    private Integer parentId;
    @Column(length = 191)
    private String keyCode;

    //many to many with role
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "PermissionRole",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    //getter and setter
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public PermissionEntity(String name, String displayName, Integer parentId, String keyCode) {
        this.name = name;
        this.displayName = displayName;
        this.parentId = parentId;
        this.keyCode = keyCode;
    }

    public PermissionEntity() {
    }
}
