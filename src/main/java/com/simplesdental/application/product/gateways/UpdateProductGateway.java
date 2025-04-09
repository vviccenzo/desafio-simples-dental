package com.simplesdental.application.product.gateways;

import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.product.dto.ProductUpdateDto;

public interface UpdateProductGateway {

    public Product execute(Long id, ProductUpdateDto productUpdateDto);

}
