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
        logger.info("Iniciando exclusão do produto com ID: {}", id);
        boolean result = this.deleteProductByIdGateway.deleteById(id);
        logger.info("Produto com ID: {} {}", id, result ? "excluído com sucesso." : "não encontrado.");
        return result;
    }
}
