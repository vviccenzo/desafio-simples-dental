package com.simplesdental.application.product.gateways;

import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.product.dto.ProductCreateDto;

public interface CreateProductGateway {

    public Product execute(ProductCreateDto product);

}
