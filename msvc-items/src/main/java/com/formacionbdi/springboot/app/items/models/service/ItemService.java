package com.formacionbdi.springboot.app.items.models.service;

import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Product;


import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);

    public Product save(Product product);

    public Product update(Product product, Long id);

    public void delete(Long id);
}
