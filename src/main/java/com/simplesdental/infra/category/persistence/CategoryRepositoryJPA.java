package com.simplesdental.infra.category.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;

import jakarta.transaction.Transactional;

@Repository
public class CategoryRepositoryJPA implements CategoryRepositoryGateway {

    private final ProductCategoryRepository categoryRepository;

    public CategoryRepositoryJPA(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(id);
        if (categoryEntity.isPresent()) {
            return Optional.of(CategoryMapper.toCategory(categoryEntity.get()));
        }

        return Optional.empty();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Category save(Category category) {
        CategoryEntity categoryEntity = CategoryMapper.toCategoryEntity(category);
        categoryEntity = this.categoryRepository.save(categoryEntity);

        return CategoryMapper.toCategory(categoryEntity);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = this.categoryRepository.findAll(pageable);
        return CategoryMapper.toCategoryPage(categoryEntities);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteById(Long id) {
        if (!this.categoryRepository.existsById(id)) {
            return false;
        }

        this.categoryRepository.deleteById(id);

        return true;
    }

}
