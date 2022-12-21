package com.formacionbdi.springboot.app.items.clients;

import com.formacionbdi.springboot.app.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-products", url = "localhost:8001")
public interface ProductRestClient {

    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/list/{id}")
    public Product detail(@PathVariable Long id);

}
