package com.simplesdental.application.product.usecases;

import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;

public class DeleteProductByIdUseCase implements DeleteProductByIdGateway {

    private final DeleteProductByIdGateway deleteProductByIdGateway;

    public DeleteProductByIdUseCase(DeleteProductByIdGateway deleteProductByIdGateway) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
    }

    @Override
    public boolean execute(Long id) {
        return this.deleteProductByIdGateway.execute(id);
    }

}
