package com.simplesdental.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplesdental.product.model.Category;
import com.simplesdental.product.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    public Optional<Category> findById(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isPresent()) {
            return category;
        }
        return this.categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean deleteById(Long id) {
        if (!this.categoryRepository.existsById(id)) {
            return false;
        }

        this.categoryRepository.deleteById(id);

        return true;
    }

    @Transactional(rollbackOn = Exception.class)
    public void update(Category category) {
        this.categoryRepository.save(category);
    }
}