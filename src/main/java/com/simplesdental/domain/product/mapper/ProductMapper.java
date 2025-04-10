package com.simplesdental.domain.product.mapper;

import java.util.List;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.category.persistence.CategoryEntity;
import com.simplesdental.infra.product.dto.ProductCreateDto;
import com.simplesdental.infra.product.dto.ProductUpdateDto;
import com.simplesdental.infra.product.persistence.ProductEntity;

public class ProductMapper {

    public static List<Product> toProductListFromEntity(List<ProductEntity> productEntities) {
        List<Product> products = productEntities.stream().map(product -> {
            Product productEntity = new Product();
            productEntity.setId(product.getId());
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());
            productEntity.setCode(product.getCode());

            return productEntity;
        }).toList();

        return products;
    }

    public static List<Product> toProductListFromDto(List<ProductCreateDto> products) {
        List<Product> productList = products.stream().map(product -> {
            Product productEntity = new Product();
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());

            return productEntity;
        }).toList();

        return productList;
    }

    public static List<ProductEntity> toProductEntityList(List<Product> products, CategoryEntity categoryEntity) {
        List<ProductEntity> productEntities = products.stream().map(product -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setStatus(product.getStatus());
            productEntity.setCode(product.getCode());
            productEntity.setCategory(categoryEntity);

            return productEntity;
        }).toList();

        return productEntities;
    }

    public static ProductEntity toProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setCode(product.getCode());

        CategoryEntity categoryEntity = CategoryMapper.toCategoryEntity(product.getCategory());
        productEntity.setCategory(categoryEntity);

        return productEntity;
    }

    public static Product toProduct(ProductEntity product) {
        Product productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setCode(product.getCode());

        Category category = CategoryMapper.toCategory(product.getCategory());
        productEntity.setCategory(category);

        return productEntity;
    }

    public static Product toProduct(ProductCreateDto product) {
        Product productDomain = new Product();
        productDomain.setName(product.getName());
        productDomain.setDescription(product.getDescription());
        productDomain.setPrice(product.getPrice());
        productDomain.setStatus(product.getStatus());

        return productDomain;
    }

    public static Product toProductUpdated(Product product, ProductUpdateDto dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());

        return product;
    }

}
