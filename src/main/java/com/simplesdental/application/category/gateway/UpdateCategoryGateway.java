package com.simplesdental.application.category.gateway;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;

public interface UpdateCategoryGateway {

    public Category execute(Long id, CategoryUpdateDto category);

}
