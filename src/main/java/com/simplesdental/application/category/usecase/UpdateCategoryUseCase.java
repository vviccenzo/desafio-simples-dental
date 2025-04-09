package com.simplesdental.application.category.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;

import jakarta.persistence.EntityNotFoundException;

public class UpdateCategoryUseCase implements UpdateCategoryGateway {

    private static final Logger logger = LogManager.getLogger(UpdateCategoryUseCase.class);
    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public UpdateCategoryUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public Category execute(Long id, CategoryUpdateDto categoryDto) {
        logger.info("Iniciando atualização da categoria com ID: {}", id);
        Category categoryToUpdate = this.categoryRepositoryGateway.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Categoria com ID: {} não encontrada", id);
                    return new EntityNotFoundException();
                });

        categoryToUpdate.setName(categoryDto.getName());
        categoryToUpdate.setDescription(categoryDto.getDescription());

        Category updated = this.categoryRepositoryGateway.save(categoryToUpdate);
        logger.info("Categoria com ID: {} atualizada com sucesso", id);
        return updated;
    }
}
