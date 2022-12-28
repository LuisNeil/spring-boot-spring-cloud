package com.formacionbdi.springboot.app.items.controllers;

import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.Product;
import com.formacionbdi.springboot.app.items.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private CircuitBreakerFactory<?,?> cbFactory;

    @Autowired
    @Qualifier("feignService")
    private ItemService service;


    @GetMapping("/list")
    public List<Item> list(){
        return service.findAll();
    }

    @GetMapping("/list/{id}/quantity/{quantity}")
    public Item detail(@PathVariable Long id, @PathVariable Integer quantity){
        return cbFactory.create("items").run(()->service.findById(id, quantity), e -> metodoAlternativo(id, quantity, e));
    }

    private Item metodoAlternativo(Long id, Integer quantity, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();

        item.setQuantity(quantity);
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(500.00);
        item.setProduct(product);
        return item;
    }
}
