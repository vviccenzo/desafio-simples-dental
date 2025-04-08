package com.simplesdental.infra.category.dto;

import java.util.List;

import com.simplesdental.infra.product.dto.ProductCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDto {

    @Size(max = 100)
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 255)
    @NotBlank(message = "Description is required")
    private String description;

    private List<ProductCreateDto> products;

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

    public List<ProductCreateDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCreateDto> products) {
        this.products = products;
    }

}
