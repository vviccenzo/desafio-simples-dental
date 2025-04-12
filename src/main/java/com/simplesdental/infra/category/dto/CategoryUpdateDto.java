package com.simplesdental.infra.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryUpdateDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 255)
    private String description;

    public CategoryUpdateDto(@NotNull(message = "Id is required") Long id,
            @NotBlank(message = "Name is required") String name, String description) {
        this.id = id;
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

}
