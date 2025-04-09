package com.simplesdental.application.product.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;

public class GetAllProductUseCase implements GetAllProductGateway {

    private ProductRepositoryGateway productRepositoryGateway;

    public GetAllProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Page<Product> execute(Pageable pageable) {
        return this.productRepositoryGateway.findAll(pageable);
    }

}
