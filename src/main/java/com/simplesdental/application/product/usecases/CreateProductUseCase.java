package com.simplesdental.application.product.usecases;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;
import com.simplesdental.infra.product.dto.ProductCreateDto;

import jakarta.persistence.EntityNotFoundException;

public class CreateProductUseCase implements CreateProductGateway {

    private static final Logger logger = LoggerFactory.getLogger(CreateProductUseCase.class);

    private final ProductRepositoryGateway productRepositoryGateway;
    private final GetCategoryByIdGateway getCategoryByIdUseCase;

    public CreateProductUseCase(ProductRepositoryGateway productRepositoryGateway,
            GetCategoryByIdGateway getCategoryByIdUseCase) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
    }

    @Override
    public Product execute(ProductCreateDto productCreateDto) {
        logger.debug("Iniciando criação do produto com dados: {}", productCreateDto);

        Optional<Category> category = this.getCategoryByIdUseCase.execute(productCreateDto.getCategoryId());

        if (category.isEmpty()) {
            logger.warn("Categoria com ID {} não encontrada.", productCreateDto.getCategoryId());
            throw new EntityNotFoundException("Categoria com ID " + productCreateDto.getCategoryId() + " não encontrada.");
        }

        Product productToCreate = ProductMapper.toProduct(productCreateDto);
        productToCreate.setCategory(category.get());

        Product savedProduct = this.productRepositoryGateway.save(productToCreate);

        logger.info("Produto criado com sucesso: {}", savedProduct);
        return savedProduct;
    }
}
