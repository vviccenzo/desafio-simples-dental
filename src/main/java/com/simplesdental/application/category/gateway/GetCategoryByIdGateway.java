package com.simplesdental.application.category.gateway;

import java.util.Optional;

import com.simplesdental.domain.category.entities.Category;

public interface GetCategoryByIdGateway {

    Optional<Category> execute(Long id);

}
