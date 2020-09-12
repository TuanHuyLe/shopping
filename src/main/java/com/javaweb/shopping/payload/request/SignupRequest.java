package com.javaweb.shopping.payload.request;

import java.util.ArrayList;
import java.util.List;

public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private List<String> roles;

    public SignupRequest() {
        roles = new ArrayList<>();
    }

    public SignupRequest(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        roles = new ArrayList<>();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
