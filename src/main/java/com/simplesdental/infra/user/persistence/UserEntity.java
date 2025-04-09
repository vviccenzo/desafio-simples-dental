package com.simplesdental.infra.user.persistence;

import com.simplesdental.product.model.Generic;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends Generic {

    private String name;

    private String email;

    private String password;

}
