package com.formacionbdi.springboot.app.items.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.formacionbdi.springboot.app.commons.models.entity.Product;


import java.util.List;

@FeignClient(name = "msvc-products")
public interface ProductRestClient {

    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/list/{id}")
    public Product detail(@PathVariable Long id);

    @PostMapping("/create")
    public Product create(@RequestBody Product product);

    @PutMapping("/edit/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);

}
