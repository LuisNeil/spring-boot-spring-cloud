package com.formacionbdi.springboot.app.products.controllers;

import com.formacionbdi.springboot.app.products.models.entity.Product;
import com.formacionbdi.springboot.app.products.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ProductService service;

    @GetMapping("/list")
    public List<Product> list(){
        return service.findAll().stream().map(p->{
            //p.setPort(Integer.valueOf(env.getProperty("local.server.port")));
            p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/list/{id}")
    public Product detail(@PathVariable Long id){
        Product product = service.findById(id);
        //product.setPort(Integer.valueOf(env.getProperty("local.server.port")));
        product.setPort(port);
        return product;
    }
}
