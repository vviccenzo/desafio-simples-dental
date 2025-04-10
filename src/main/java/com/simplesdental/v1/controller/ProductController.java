package com.simplesdental.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.v1.dto.ProductDTO;
import com.simplesdental.v1.model.Product;
import com.simplesdental.v1.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Tag(name = "v1.Produtos", description = "Endpoints relacionados a operações com produtos v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Transactional
    public Page<ProductDTO> getAllProducts(@Valid Pageable pageable) {
        return this.productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return this.productService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product) {
        return this.productService.save(product);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid
    // @RequestBody Product product) {
    // // return this.productService.findById(id);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return this.productService.deleteById(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}