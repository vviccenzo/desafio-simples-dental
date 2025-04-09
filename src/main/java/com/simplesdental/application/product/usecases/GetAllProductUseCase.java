package com.simplesdental.application.product.usecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;

public class GetAllProductUseCase implements GetAllProductGateway {

    private static final Logger logger = LogManager.getLogger(GetAllProductUseCase.class);
    private final ProductRepositoryGateway productRepositoryGateway;

    public GetAllProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Page<Product> execute(Pageable pageable) {
        logger.info("Buscando todos os produtos com paginação: {}", pageable);
        Page<Product> result = this.productRepositoryGateway.findAll(pageable);
        logger.info("Produtos encontrados: {}", result.getTotalElements());
        return result;
    }
}
