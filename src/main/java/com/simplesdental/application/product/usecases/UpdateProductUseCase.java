package com.simplesdental.application.product.usecases;

import java.util.Optional;

import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;
import com.simplesdental.infra.product.dto.ProductUpdateDto;

import jakarta.persistence.EntityNotFoundException;

public class UpdateProductUseCase implements UpdateProductGateway {

    private final ProductRepositoryGateway productRepositoryGateway;

    public UpdateProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Product execute(Long id, ProductUpdateDto productUpdateDto) {
        Optional<Product> product = this.productRepositoryGateway.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Product productUpdated = ProductMapper.toProductUpdated(product.get(), productUpdateDto);
        return this.productRepositoryGateway.save(productUpdated);
    }

}
