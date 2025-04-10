package com.simplesdental.infra.user.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simplesdental.infra.user.persistence.UserRole;

public class UserContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    private UserRole role;

    public UserContext() {
    }

    public UserContext(String name, String email, UserRole role, String token) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
