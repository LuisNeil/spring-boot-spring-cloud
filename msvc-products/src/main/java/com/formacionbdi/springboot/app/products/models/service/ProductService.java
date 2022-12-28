package com.formacionbdi.springboot.app.products.models.service;

import com.formacionbdi.springboot.app.products.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);
}