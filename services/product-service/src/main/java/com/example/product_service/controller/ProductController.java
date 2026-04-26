package com.example.product_service.controller;
import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.save(product);
    }
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}