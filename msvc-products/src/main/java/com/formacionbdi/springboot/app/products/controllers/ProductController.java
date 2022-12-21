package com.formacionbdi.springboot.app.products.controllers;

import com.formacionbdi.springboot.app.products.models.entity.Product;
import com.formacionbdi.springboot.app.products.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/list")
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/list/{id}")
    public Product detail(@PathVariable Long id){
        return service.findById(id);
    }
}
