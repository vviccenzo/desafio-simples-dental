package com.simplesdental.domain.category.mapper;

import java.util.List;

import org.springframework.data.domain.Page;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.product.entities.Product;
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

        List<Product> products = categoryCreateDto.getProducts().stream().map(product -> {
            Product productEntity = new Product();
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());
            productEntity.setCode("CÓDIGO" + Math.random() * 1000);

            return productEntity;
        }).toList();

        category.setProducts(products);

        return category;
    }

    public static Category toCategory(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDescription(categoryEntity.getName());

        List<Product> products = categoryEntity.getProducts().stream().map(product -> {
            Product productEntity = new Product();
            productEntity.setId(product.getId());
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());

            if (product.getCode() == null) {
                productEntity.setCode("CÓDIGO" + Math.random() * 1000);
            } else {
                productEntity.setCode(product.getCode());
            }

            return productEntity;
        }).toList();

        category.setProducts(products);

        return category;
    }

    public static CategoryEntity toCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        List<ProductEntity> products = category.getProducts().stream().map(product -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());
            productEntity.setCode("CÓDIGO" + Math.random() * 1000);
            productEntity.setCategory(categoryEntity);

            return productEntity;
        }).toList();

        categoryEntity.setProducts(products);

        return categoryEntity;
    }

}
