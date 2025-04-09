package com.simplesdental.infra.product.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.simplesdental.application.product.gateways.ProductRepositoryGateway;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.domain.product.mapper.ProductMapper;

import jakarta.transaction.Transactional;

@Repository
public class ProductRepositoryJPA implements ProductRepositoryGateway {

    private final ProductRepositoryy productRepository;

    public ProductRepositoryJPA(ProductRepositoryy productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Product save(Product product) {
        ProductEntity productEntity = ProductMapper.toProductEntity(product);
        productEntity = this.productRepository.save(productEntity);

        Product productSaved = ProductMapper.toProduct(productEntity);

        return productSaved;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            Product product = ProductMapper.toProduct(productEntity.get());
            return Optional.of(product);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteById(Long id) {
        if (!this.productRepository.existsById(id)) {
            return false;
        }

        this.productRepository.deleteById(id);

        return true;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<ProductEntity> productEntities = this.productRepository.findAll(pageable);
        return productEntities.map(ProductMapper::toProduct);
    }

}
