package com.simplesdental.infra.product.dto;

import java.math.BigDecimal;

import com.simplesdental.infra.category.dto.CategoryCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductCreateDto {

    @Size(max = 100)
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 255)
    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Code is required")
    private int code;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private CategoryCreateDto category;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryCreateDto getCategory() {
        return category;
    }

    public void setCategory(CategoryCreateDto category) {
        this.category = category;
    }

}
