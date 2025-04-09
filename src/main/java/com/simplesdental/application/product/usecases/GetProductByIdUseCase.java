package com.simplesdental.application.product.usecases;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;

public class GetProductByIdUseCase implements GetProductByIdGateway {

    private static final Logger logger = LogManager.getLogger(GetProductByIdUseCase.class);
    private final ProductRepositoryGateway productRepositoryGateway;

    public GetProductByIdUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Optional<Product> execute(Long id) {
        logger.info("Buscando produto com ID: {}", id);
        Optional<Product> result = this.productRepositoryGateway.findById(id);
        logger.info("Produto {}encontrado com ID: {}", result.isPresent() ? "" : "não ", id);
        return result;
    }
}
