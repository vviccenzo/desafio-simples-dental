package com.simplesdental.application.product.usecases;

import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;

public class DeleteProductByIdUseCase implements DeleteProductByIdGateway {

    private final ProductRepositoryGateway deleteProductByIdGateway;

    public DeleteProductByIdUseCase(ProductRepositoryGateway deleteProductByIdGateway) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
    }

    @Override
    public boolean execute(Long id) {
        return this.deleteProductByIdGateway.deleteById(id);
    }

}
