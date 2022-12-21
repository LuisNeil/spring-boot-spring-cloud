package com.formacionbdi.springboot.app.products.models.repository;

import com.formacionbdi.springboot.app.products.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
