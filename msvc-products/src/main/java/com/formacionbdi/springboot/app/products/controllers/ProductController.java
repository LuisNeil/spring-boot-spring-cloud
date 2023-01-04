package com.formacionbdi.springboot.app.products.controllers;

import com.formacionbdi.springboot.app.commons.models.entity.Product;
import com.formacionbdi.springboot.app.products.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
    public Product detail(@PathVariable Long id) throws InterruptedException {
        if(id.equals(10L)){
            throw new IllegalStateException("Product Not Found");
        }

        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }

        Product product = service.findById(id);
        //product.setPort(Integer.valueOf(env.getProperty("local.server.port")));
        product.setPort(port);
        return product;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return service.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id){
        Product productDb = service.findById(id);
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());

        return service.save(productDb);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
