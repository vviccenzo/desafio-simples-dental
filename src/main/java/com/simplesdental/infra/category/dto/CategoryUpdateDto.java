package com.simplesdental.infra.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryUpdateDto {

    @NotNull(message = "O ID é obrigatório")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    public CategoryUpdateDto(@NotNull(message = "O ID é obrigatório") Long id,
            @NotBlank(message = "O nome é obrigatório") String name,
            @NotBlank(message = "A descrição é obrigatória") String description) {
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
