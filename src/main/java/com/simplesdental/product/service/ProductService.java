package com.simplesdental.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simplesdental.product.model.Product;
import com.simplesdental.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public ResponseEntity<Product> findById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional(rollbackOn = Exception.class)
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean deleteById(Long id) {
        if (!this.productRepository.existsById(id)) {
            return false;
        }

        this.productRepository.deleteById(id);

        return true;
    }
}