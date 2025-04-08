package com.simplesdental.application.category.usecase;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;

import jakarta.persistence.EntityNotFoundException;

public class UpdateCategoryUseCase implements UpdateCategoryGateway {

    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public UpdateCategoryUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public Category execute(Long id, CategoryUpdateDto category) {
        Category categoryToUpdate = this.categoryRepositoryGateway.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());

        return this.categoryRepositoryGateway.save(categoryToUpdate);

    }

}
