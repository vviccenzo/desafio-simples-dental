package com.simplesdental.application.product.gateways;

import com.simplesdental.domain.product.entities.Product;

public interface CreateProductGateway {

    public Product execute(Product product);

}
