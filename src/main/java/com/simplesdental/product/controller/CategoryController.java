package com.simplesdental.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.domain.category.entities.Category;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final GetAllCategoryGateway getAllCategoryGateway;

    @Autowired
    public CategoryController(GetAllCategoryGateway getAllCategoryGateway) {
        this.getAllCategoryGateway = getAllCategoryGateway;
    }

    @GetMapping
    public Page<Category> getAllCategories(@Valid Pageable pageable) {
        return this.getAllCategoryGateway.execute(pageable);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    // return this.categoryService.findById(id)
    // .map(ResponseEntity::ok)
    // .orElse(ResponseEntity.notFound().build());
    // }

    // @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public Category createCategory(@Valid @RequestBody Category category) {
    // return this.categoryService.save(category);
    // }

    // @PutMapping("/{id}")
    // public void updateCategory(@PathVariable Long id, @Valid @RequestBody
    // Category category) {
    // this.categoryService.update(category);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    // return this.categoryService.deleteById(id) ?
    // ResponseEntity.noContent().build()
    // : ResponseEntity.notFound().build();
    // }
}