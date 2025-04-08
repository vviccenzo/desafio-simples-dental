package com.simplesdental.application.category.gateway;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryCreateDto;

public interface CreateCategoryGateway {

    public Category execute(CategoryCreateDto category);

}
