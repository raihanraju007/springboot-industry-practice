package com.raju.enterprise.springboot_industry_practice.repository;

import com.raju.enterprise.springboot_industry_practice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
