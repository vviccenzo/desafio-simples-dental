package com.simplesdental.infra.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryy extends JpaRepository<ProductEntity, Long>{
    
}
