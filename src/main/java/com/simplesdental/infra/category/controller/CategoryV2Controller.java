package com.simplesdental.infra.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;
import com.simplesdental.application.category.gateway.FindByIdCategoryGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/categories")
public class CategoryV2Controller {

    private final CreateCategoryGateway createCategoryGateway;
    private final UpdateCategoryGateway updateCategoryGateway;
    private final GetAllCategoryGateway getAllCategoryGateway;
    private final FindByIdCategoryGateway findByIdCategoryGateway;
    private final DeleteCategoryByIdGateway deleteCategoryByIdGateway;

    public CategoryV2Controller(
            GetAllCategoryGateway getAllCategoryGateway,
            FindByIdCategoryGateway findByIdCategoryGateway,
            CreateCategoryGateway createCategoryGateway,
            UpdateCategoryGateway updateCategoryGateway,
            DeleteCategoryByIdGateway deleteCategoryByIdGateway) {
        this.getAllCategoryGateway = getAllCategoryGateway;
        this.findByIdCategoryGateway = findByIdCategoryGateway;
        this.createCategoryGateway = createCategoryGateway;
        this.updateCategoryGateway = updateCategoryGateway;
        this.deleteCategoryByIdGateway = deleteCategoryByIdGateway;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Category> getAllCategories(@Valid Pageable pageable) {
        return this.getAllCategoryGateway.execute(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return this.findByIdCategoryGateway.execute(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody CategoryCreateDto category) {
        return this.createCategoryGateway.execute(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDto category) {
        this.updateCategoryGateway.execute(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return this.deleteCategoryByIdGateway.execute(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
