package com.simplesdental.application.product.usecases;

import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteProductByIdUseCase implements DeleteProductByIdGateway {

    private static final Logger logger = LogManager.getLogger(DeleteProductByIdUseCase.class);
    private final ProductRepositoryGateway deleteProductByIdGateway;

    public DeleteProductByIdUseCase(ProductRepositoryGateway deleteProductByIdGateway) {
        this.deleteProductByIdGateway = deleteProductByIdGateway;
    }

    @Override
    public boolean execute(Long id) {
        boolean result = this.deleteProductByIdGateway.deleteById(id);
        logger.info("Product with id: {} {}", id, result ? "excluded." : "not founded.");
        return result;
    }
}
