package com.simplesdental.domain.category.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simplesdental.domain.product.entities.Product;

public class Category {

    public Long id;

    public String name;

    public String description;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Product> products;

    public Category() {
    }

    public Category(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Category(String name, String description, List<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
