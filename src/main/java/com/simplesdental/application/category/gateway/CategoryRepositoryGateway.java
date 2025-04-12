package com.simplesdental.application.category.gateway;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.category.entities.Category;

public interface CategoryRepositoryGateway {

    Optional<Category> findById(Long id);

    Category save(Category category);

    Page<Category> findAll(Pageable pageable);

    boolean deleteById(Long id);

}
