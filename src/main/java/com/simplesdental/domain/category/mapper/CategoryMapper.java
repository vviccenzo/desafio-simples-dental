package com.simplesdental.domain.category.mapper;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import com.simplesdental.infra.category.persistence.CategoryEntity;
import com.simplesdental.infra.product.persistence.ProductEntity;

public class CategoryMapper {

    public static Page<Category> toCategoryPage(Page<CategoryEntity> categoryEntities) {
        Page<Category> categories = categoryEntities.map(CategoryMapper::toCategory);
        return categories;
    }

    public static Category toDomain(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        category.setDescription(categoryCreateDto.getName());

        List<Product> products = ProductMapper.toProductListFromDto(categoryCreateDto.getProducts());
        category.setProducts(products);

        return category;
    }

    public static Category toCategory(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDescription(categoryEntity.getName());

        List<Product> products = ProductMapper.toProductListFromEntity(categoryEntity.getProducts());
        category.setProducts(products);

        return category;
    }

    public static CategoryEntity toCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        if (Objects.nonNull(category.getId())) {
            categoryEntity.setId(category.getId());
        }

        List<ProductEntity> products = ProductMapper.toProductEntityList(category.getProducts(), categoryEntity);
        categoryEntity.setProducts(products);

        return categoryEntity;
    }

}
