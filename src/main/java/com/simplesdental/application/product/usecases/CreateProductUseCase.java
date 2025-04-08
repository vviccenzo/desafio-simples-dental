package com.simplesdental.application.product.usecases;

import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;

public class CreateProductUseCase implements CreateProductGateway {

    private final ProductRepositoryGateway productRepositoryGateway;

    public CreateProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    @Override
    public Product execute(Product product) {
        return this.productRepositoryGateway.save(product);
    }

}
