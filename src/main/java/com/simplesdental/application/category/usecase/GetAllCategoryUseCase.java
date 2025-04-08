package com.simplesdental.application.category.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.domain.category.entities.Category;

public class GetAllCategoryUseCase implements GetAllCategoryGateway {

    private final CategoryRepositoryGateway categoryRepository;

    public GetAllCategoryUseCase(CategoryRepositoryGateway categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> execute(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

}
