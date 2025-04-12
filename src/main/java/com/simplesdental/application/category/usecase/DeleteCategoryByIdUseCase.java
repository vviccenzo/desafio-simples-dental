package com.simplesdental.application.category.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;

public class DeleteCategoryByIdUseCase implements DeleteCategoryByIdGateway {

    private static final Logger logger = LogManager.getLogger(DeleteCategoryByIdUseCase.class);
    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public DeleteCategoryByIdUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public boolean execute(Long id) {
        boolean deleted = this.categoryRepositoryGateway.deleteById(id);
        logger.info("Category with: {} {}", id, deleted ? "excluded." : "not founded.");
        return deleted;
    }
}
