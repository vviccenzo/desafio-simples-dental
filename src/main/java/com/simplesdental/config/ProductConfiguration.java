package com.simplesdental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.application.product.usecases.CreateProductUseCase;

@Configuration
public class ProductConfiguration {

    @Bean
    public CreateProductGateway createProductGateway(ProductRepositoryGateway productRepositoryGateway) {
        return new CreateProductUseCase(productRepositoryGateway);
    }

}
