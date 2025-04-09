package com.simplesdental.application.category.usecase;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.domain.category.entities.Category;

public class GetByIdCategoryUseCase implements GetCategoryByIdGateway {

    private static final Logger logger = LogManager.getLogger(GetByIdCategoryUseCase.class);

    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public GetByIdCategoryUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public Optional<Category> execute(Long id) {
        logger.info("Buscando produto com ID: {}", id);
        return this.categoryRepositoryGateway.findById(id);
    }

}
