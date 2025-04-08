package com.simplesdental.domain.category.mapper;

import org.springframework.data.domain.Page;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import com.simplesdental.infra.category.persistence.CategoryEntity;

public class CategoryMapper {

    public static Page<Category> toCategoryPage(Page<CategoryEntity> categoryEntities) {
        throw new UnsupportedOperationException("Unimplemented method 'toCategoryPage'");
    }

    public static Category toDomain(CategoryCreateDto categoryCreateDto) {
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

}
