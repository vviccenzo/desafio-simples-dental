package com.simplesdental.application.product.gateways;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplesdental.domain.product.entities.Product;

public interface ProductRepositoryGateway {

    public Product save(Product product);

    public Optional<Product> findById(Long id);

    public void deleteById(Long id);

    public Page<Product> findAll(Pageable pageable);

}
