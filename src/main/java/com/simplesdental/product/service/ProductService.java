package com.simplesdental.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplesdental.product.model.Product;
import com.simplesdental.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        log.info("Buscando todos os produtos. Página: {}, Tamanho: {}", page, size);
        return this.productRepository.findAll(pageable);
    }

    public Optional<Product> findById(Long id) {
        log.info("Buscando produto com ID: {}", id);
        return this.productRepository.findById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Product save(Product product) {
        log.info("Salvando produto: {}", product);

        try {
            return this.productRepository.save(product);
        } catch (Exception e) {
            log.error("Erro ao salvar produto: {}", product, e);
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean deleteById(Long id) {
        log.info("Tentando deletar produto com ID: {}", id);

        if (!this.productRepository.existsById(id)) {
            log.warn("Produto com ID {} não encontrado. Não será deletado.", id);
        }

        this.productRepository.deleteById(id);

        return true;
    }
}