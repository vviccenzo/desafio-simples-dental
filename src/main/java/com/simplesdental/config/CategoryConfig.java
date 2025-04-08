package com.simplesdental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;
import com.simplesdental.application.category.gateway.FindByIdCategoryGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.application.category.usecase.CreateCategoryUseCase;
import com.simplesdental.application.category.usecase.DeleteCategoryByIdUseCase;
import com.simplesdental.application.category.usecase.FindByIdCategoryUseCase;
import com.simplesdental.application.category.usecase.GetAllCategoryUseCase;
import com.simplesdental.application.category.usecase.UpdateCategoryUseCase;

@Configuration
public class CategoryConfig {
    
    @Bean
    public GetAllCategoryGateway getAllCategoryGateway(CategoryRepositoryGateway categoryRepositoryGateway) {
        return new GetAllCategoryUseCase(categoryRepositoryGateway);
    }

    @Bean
    public FindByIdCategoryGateway findByIdCategoryGateway(CategoryRepositoryGateway categoryRepositoryGateway) {
        return new FindByIdCategoryUseCase(categoryRepositoryGateway);
    }

    @Bean
    public CreateCategoryGateway createCategoryGateway(CategoryRepositoryGateway categoryRepositoryGateway) {
        return new CreateCategoryUseCase(categoryRepositoryGateway); 
    }

    @Bean
    public UpdateCategoryGateway updateCategoryGateway(CategoryRepositoryGateway categoryRepositoryGateway) {
        return new UpdateCategoryUseCase(categoryRepositoryGateway); 
    }

    @Bean
    public DeleteCategoryByIdGateway deleteCategoryByIdGateway(CategoryRepositoryGateway categoryRepositoryGateway) {
        return new DeleteCategoryByIdUseCase(categoryRepositoryGateway);
    }
}
