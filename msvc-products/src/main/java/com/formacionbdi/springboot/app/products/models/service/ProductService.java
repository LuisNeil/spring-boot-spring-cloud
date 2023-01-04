package com.formacionbdi.springboot.app.products.models.service;

import com.formacionbdi.springboot.app.commons.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);

    public Product save(Product product);

    public void deleteById(Long id);
}
