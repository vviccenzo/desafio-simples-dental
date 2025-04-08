package com.simplesdental.application.product.gateways;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.product.entities.Product;

public interface GetAllProductGateway {

    public Page<Product> execute(Pageable pageable);
    
}
