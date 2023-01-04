package com.formacionbdi.springboot.app.items.controllers;

import com.formacionbdi.springboot.app.items.models.Item;
import com.formacionbdi.springboot.app.items.models.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.formacionbdi.springboot.app.commons.models.entity.Product;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;

    @Autowired
    private CircuitBreakerFactory<?,?> cbFactory;

    @Autowired
    @Qualifier("feignService")
    private ItemService service;

    @Value("${configuration.text}")
    private String text;

    @GetMapping("/list")
    public List<Item> list(){
        return service.findAll();
    }

    @GetMapping("/list/{id}/quantity/{quantity}")
    public Item detail(@PathVariable Long id, @PathVariable Integer quantity){
        return cbFactory.create("items").run(()->service.findById(id, quantity), e -> metodoAlternativo(id, quantity, e));
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/list2/{id}/quantity/{quantity}")
    public Item detail2(@PathVariable Long id, @PathVariable Integer quantity){
        return service.findById(id, quantity);
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/list3/{id}/quantity/{quantity}")
    public CompletableFuture<Item> detail3(@PathVariable Long id, @PathVariable Integer quantity){
        return CompletableFuture.supplyAsync(() -> service.findById(id, quantity));
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

    private CompletableFuture<Item> metodoAlternativo2(Long id, Integer quantity, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();

        item.setQuantity(quantity);
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(500.00);
        item.setProduct(product);
        return CompletableFuture.supplyAsync(() -> item);
    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
        logger.info(text);
        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("port", port);

        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
        json.put("author.name", env.getProperty("configuration.author.name"));
            json.put("author.email", env.getProperty("configuration.author.email"));
        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return service.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id){
        return service.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
