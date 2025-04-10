package com.simplesdental.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesdental.v1.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}