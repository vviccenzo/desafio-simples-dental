package com.simplesdental.infra.category.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;

@Repository
public class CategoryRepositoryJPA implements CategoryRepositoryGateway {

    private final CategoryRepository categoryRepository;

    public CategoryRepositoryJPA(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Category save(Category category) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = this.categoryRepository.findAll(pageable);
        return CategoryMapper.toCategoryPage(categoryEntities);
    }

}
