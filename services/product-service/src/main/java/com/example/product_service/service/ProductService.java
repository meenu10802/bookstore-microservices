package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {

        return repository.save(product);
    }

    public List<Product> getAll() {

        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Product update(Long id, Product updatedProduct) {
        Product existing = repository.findById(id).orElseThrow();

        existing.setTitle(updatedProduct.getTitle());
        existing.setPrice(updatedProduct.getPrice());
        existing.setAuthor(updatedProduct.getAuthor());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}