package com.simplesdental.infra.user.persistence;

import java.util.List;

import com.simplesdental.infra.category.persistence.CategoryEntity;
import com.simplesdental.infra.generic.persistence.Generic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity extends Generic {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "role", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<CategoryEntity> categories;

}
