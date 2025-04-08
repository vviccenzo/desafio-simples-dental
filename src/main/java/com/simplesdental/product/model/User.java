package com.simplesdental.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends Generic {

    @NotBlank
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

}
