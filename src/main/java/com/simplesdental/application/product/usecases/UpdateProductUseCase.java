package com.simplesdental.application.product.usecases;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.application.product.mapper.ProductMapper;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.product.dto.ProductUpdateDto;

import jakarta.persistence.EntityNotFoundException;

public class UpdateProductUseCase implements UpdateProductGateway {

    private static final Logger logger = LogManager.getLogger(UpdateProductUseCase.class);
    private final ProductRepositoryGateway productRepositoryGateway;

    public UpdateProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Product execute(Long id, ProductUpdateDto productUpdateDto) {
        Optional<Product> product = this.productRepositoryGateway.findById(id);
        if (product.isEmpty()) {
            logger.warn("Product with id: {} not founded.", id);
            throw new EntityNotFoundException();
        }

        Product productUpdated = ProductMapper.toProductUpdated(product.get(), productUpdateDto);
        Product saved = this.productRepositoryGateway.save(productUpdated);
        logger.info("Product with id: {} updated.", id);
        return saved;
    }
}
