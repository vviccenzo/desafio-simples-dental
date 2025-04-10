package com.simplesdental.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesdental.v1.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}