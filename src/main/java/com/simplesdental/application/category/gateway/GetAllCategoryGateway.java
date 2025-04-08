package com.simplesdental.application.category.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.category.entities.Category;

public interface GetAllCategoryGateway {

    public Page<Category> execute(Pageable pageable);

}
