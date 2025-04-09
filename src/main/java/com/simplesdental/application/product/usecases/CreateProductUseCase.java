package com.simplesdental.application.product.usecases;

import java.util.Optional;

import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;
import com.simplesdental.infra.product.dto.ProductCreateDto;

import jakarta.persistence.EntityNotFoundException;

public class CreateProductUseCase implements CreateProductGateway {

    private final ProductRepositoryGateway productRepositoryGateway;

    private final GetCategoryByIdGateway getCategoryByIdUseCase;

    public CreateProductUseCase(ProductRepositoryGateway productRepositoryGateway,
            GetCategoryByIdGateway getCategoryByIdUseCase) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
    }

    @Override
    public Product execute(ProductCreateDto productCreateDto) {
        Optional<Category> category = this.getCategoryByIdUseCase.execute(productCreateDto.getCategoryId());
        if (category.isEmpty()) {
            throw new EntityNotFoundException("Categoria com ID " + productCreateDto.getCategoryId() + " não encontrada.");
        }

        Product productToCreate = ProductMapper.toProduct(productCreateDto);
        productToCreate.setCategory(category.get());

        return this.productRepositoryGateway.save(productToCreate);
    }

}
