package com.simplesdental.application.category.usecase;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;
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
        logger.info("Iniciando criação de categoria: {}", categoryCreateDto);

        try {
            Category category = CategoryMapper.toDomain(categoryCreateDto);
            Category savedCategory = this.categoryRepository.save(category);

            logger.info("Categoria criada com sucesso: {}", savedCategory);
            return savedCategory;
        } catch (Exception e) {
            logger.error("Erro ao criar categoria: {}", e.getMessage(), e);
            throw e;
        }
    }
}
