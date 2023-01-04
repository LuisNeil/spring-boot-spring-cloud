package com.formacionbdi.springboot.app.items.models.service;

import com.formacionbdi.springboot.app.items.clients.ProductRestClient;
import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("feignService")
public class ItemServiceFeign implements ItemService{

    @Autowired
    ProductRestClient feignClient;

    @Override
    public List<Item> findAll() {
        return feignClient.list().stream().map(product -> new Item(product,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(feignClient.detail(id), quantity);
    }

    @Override
    public Product save(Product product) {
        return feignClient.create(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return feignClient.update(product, id);
    }

    @Override
    public void delete(Long id) {
        feignClient.delete(id);
    }

}
