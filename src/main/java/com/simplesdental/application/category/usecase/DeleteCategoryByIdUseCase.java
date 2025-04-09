package com.simplesdental.application.category.usecase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplesdental.application.category.gateway.CategoryRepositoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;

public class DeleteCategoryByIdUseCase implements DeleteCategoryByIdGateway {

    private static final Logger logger = LogManager.getLogger(DeleteCategoryByIdUseCase.class);
    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public DeleteCategoryByIdUseCase(CategoryRepositoryGateway categoryRepositoryGateway) {
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    @Override
    public boolean execute(Long id) {
        logger.info("Iniciando exclusão da categoria com ID: {}", id);
        boolean deleted = this.categoryRepositoryGateway.deleteById(id);
        logger.info("Categoria com ID: {} {}", id, deleted ? "excluída com sucesso." : "não encontrada.");
        return deleted;
    }
}
