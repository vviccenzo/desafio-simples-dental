package com.simplesdental.application.category.usecase;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;

public class DeleteCategoryByIdUseCase implements DeleteCategoryByIdGateway {

    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public DeleteCategoryByIdUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public boolean execute(Long id) {
        return this.categoryRepositoryGateway.deleteById(id);
    }

}
