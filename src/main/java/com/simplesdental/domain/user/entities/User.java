package com.simplesdental.domain.user.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.user.persistence.UserRole;

public class User {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

    private List<Category> categories = new ArrayList<>();

    private Collection<UserRole> authorities = new ArrayList<>();

    public User() {

    }

    public User(Long id, String name, String email, String password, UserRole role, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Collection<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<UserRole> authorities) {
        this.authorities = authorities;
    }

}
