package com.simplesdental.product.service;

import com.simplesdental.product.model.Category;
import com.simplesdental.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}