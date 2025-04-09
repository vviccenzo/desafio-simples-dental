package com.simplesdental.application.product.usecases;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;
import com.simplesdental.infra.product.dto.ProductUpdateDto;

import jakarta.persistence.EntityNotFoundException;

public class UpdateProductUseCase implements UpdateProductGateway {

    private static final Logger logger = LogManager.getLogger(UpdateProductUseCase.class);
    private final ProductRepositoryGateway productRepositoryGateway;

    public UpdateProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Product execute(Long id, ProductUpdateDto productUpdateDto) {
        logger.info("Iniciando atualização do produto com ID: {}", id);
        Optional<Product> product = this.productRepositoryGateway.findById(id);
        if (product.isEmpty()) {
            logger.warn("Produto com ID: {} não encontrado para atualização", id);
            throw new EntityNotFoundException();
        }

        Product productUpdated = ProductMapper.toProductUpdated(product.get(), productUpdateDto);
        Product saved = this.productRepositoryGateway.save(productUpdated);
        logger.info("Produto com ID: {} atualizado com sucesso", id);
        return saved;
    }
}
