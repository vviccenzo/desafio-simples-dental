package com.simplesdental.application.category.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.domain.category.entities.Category;

public class GetAllCategoryUseCase implements GetAllCategoryGateway {

    private static final Logger logger = LogManager.getLogger(GetAllCategoryUseCase.class);
    private final CategoryRepositoryGateway categoryRepository;

    public GetAllCategoryUseCase(CategoryRepositoryGateway categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> execute(Pageable pageable) {
        logger.info("Buscando todas as categorias com paginação: {}", pageable);
        Page<Category> result = this.categoryRepository.findAll(pageable);
        logger.info("Total de categorias encontradas: {}", result.getTotalElements());
        return result;
    }
}
