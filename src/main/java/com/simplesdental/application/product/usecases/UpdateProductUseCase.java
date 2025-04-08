package com.simplesdental.application.product.usecases;

import java.util.Optional;

import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;

public class UpdateProductUseCase implements UpdateProductGateway {

    private final ProductRepositoryGateway productRepositoryGateway;

    public UpdateProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Optional<Product> execute(Long id) {
        return this.productRepositoryGateway.findById(id);
    }

}
