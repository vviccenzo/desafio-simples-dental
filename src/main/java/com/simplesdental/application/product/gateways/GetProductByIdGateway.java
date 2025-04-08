package com.simplesdental.application.product.gateways;

import java.util.Optional;

import com.simplesdental.domain.product.entities.Product;

public interface GetProductByIdGateway {

    public Optional<Product> execute(Long id);

}
