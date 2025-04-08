package com.simplesdental.application.product.usecases;

import java.util.Optional;

import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;

public class GetProductByIdUseCase implements GetProductByIdGateway {

    private final ProductRepositoryGateway productRepositoryGateway;

    public GetProductByIdUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Optional<Product> execute(Long id) {
        return this.productRepositoryGateway.findById(id);
    }

}
