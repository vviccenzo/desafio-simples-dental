package com.simplesdental.application.category.usecase;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.application.category.mapper.CategoryMapper;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateCategoryUseCase implements CreateCategoryGateway {

    private static final Logger logger = LogManager.getLogger(CreateCategoryUseCase.class);

    private final CategoryRepositoryGateway categoryRepository;

    public CreateCategoryUseCase(CategoryRepositoryGateway categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(CategoryCreateDto categoryCreateDto) {
        try {
            Category category = CategoryMapper.toDomain(categoryCreateDto);
            Category savedCategory = this.categoryRepository.save(category);

            logger.info("Category created: {}", savedCategory);
            return savedCategory;
        } catch (Exception e) {
            logger.error("Error creating category: {}", e.getMessage(), e);
            throw e;
        }
    }
}
