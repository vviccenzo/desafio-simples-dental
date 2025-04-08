package com.simplesdental.application.category.usecase;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;
import com.simplesdental.infra.category.dto.CategoryCreateDto;

public class CreateCategoryUseCase implements CreateCategoryGateway {

    private final CategoryRepositoryGateway categoryRepository;

    public CreateCategoryUseCase(CategoryRepositoryGateway categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(CategoryCreateDto categoryCreateDto) {
        Category category = CategoryMapper.toDomain(categoryCreateDto);
        return this.categoryRepository.save(category);
    }

}
