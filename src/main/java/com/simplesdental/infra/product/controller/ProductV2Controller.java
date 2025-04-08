package com.simplesdental.infra.product.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/products")
public class ProductV2Controller {

    private final GetAllProductGateway getAllProductGateway;
    private final GetProductByIdGateway getProductByIdGateway;
    private final CreateProductGateway createProductGateway;
    private final UpdateProductGateway updateProductGateway;
    private final DeleteProductByIdGateway deleteProductByIdGateway;

    public ProductV2Controller(
            GetAllProductGateway getAllProductGateway, GetProductByIdGateway getProductByIdGateway,
            CreateProductGateway createProductGateway, UpdateProductGateway updateProductGateway,
            DeleteProductByIdGateway deleteProductByIdGateway) {
        this.getAllProductGateway = getAllProductGateway;
        this.getProductByIdGateway = getProductByIdGateway;
        this.createProductGateway = createProductGateway;
        this.updateProductGateway = updateProductGateway;
        this.deleteProductByIdGateway = deleteProductByIdGateway;
    }

    @GetMapping
    @Transactional
    public Page<Product> getAllProducts(@Valid Pageable pageable) {
        return this.getAllProductGateway.execute(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return this.getProductByIdGateway.execute(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product) {
        return this.createProductGateway.execute(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return this.updateProductGateway.execute(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return this.deleteProductByIdGateway.execute(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
