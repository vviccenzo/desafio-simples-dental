package com.simplesdental.infra.category.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
}
