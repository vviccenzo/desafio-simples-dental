package com.simplesdental.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.application.product.usecases.CreateProductUseCase;
import com.simplesdental.application.product.usecases.DeleteProductByIdUseCase;
import com.simplesdental.application.product.usecases.GetAllProductUseCase;
import com.simplesdental.application.product.usecases.GetProductByIdUseCase;
import com.simplesdental.application.product.usecases.UpdateProductUseCase;

@Configuration
public class ProductConfiguration {

    @Bean
    public CreateProductGateway createProductGateway(ProductRepositoryGateway productRepositoryGateway,
            GetCategoryByIdGateway getCategoryByIdGateway) {
        return new CreateProductUseCase(productRepositoryGateway, getCategoryByIdGateway);
    }

    @Bean
    public GetAllProductGateway getAllProductGateway(ProductRepositoryGateway productRepositoryGateway) {
        return new GetAllProductUseCase(productRepositoryGateway);
    };

    @Bean
    public GetProductByIdGateway getProductByIdGateway(ProductRepositoryGateway productRepositoryGateway) {
        return new GetProductByIdUseCase(productRepositoryGateway);
    };

    @Bean
    public UpdateProductGateway updateProductGateway(ProductRepositoryGateway productRepositoryGateway) {
        return new UpdateProductUseCase(productRepositoryGateway);
    };

    @Bean
    public DeleteProductByIdGateway deleteProductByIdGateway(ProductRepositoryGateway productRepositoryGateway) {
        return new DeleteProductByIdUseCase(productRepositoryGateway);
    };
}
