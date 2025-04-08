package com.simplesdental.application.category.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.category.entities.Category;

public interface CategoryRepositoryGateway {

    public Category findById(Long id);

    public Category save(Category category);

    public Page<Category> findAll(Pageable pageable);

}
