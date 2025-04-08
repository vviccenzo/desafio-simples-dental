package com.simplesdental.application.category.gateway;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.category.entities.Category;

public interface CategoryRepositoryGateway {

    public Optional<Category> findById(Long id);

    public Category save(Category category);

    public Page<Category> findAll(Pageable pageable);

    public boolean deleteById(Long id);

}
