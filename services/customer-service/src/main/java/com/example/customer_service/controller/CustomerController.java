package com.example.customer_service.controller;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // Profile

    @GetMapping("/details")
    public Customer get(@RequestParam String userId) {
        return service.getDetails(userId);
    }

    @PostMapping("/details")
    public Customer create(
            @RequestParam String userId,
            @RequestBody Customer customer
    ) {
        return service.createCustomer(userId, customer);
    }

    @PutMapping("/details")
    public Customer update(
            @RequestParam String userId,
            @RequestBody Customer customer
    ) {
        return service.updateCustomer(userId, customer);
    }

    // Address

    @PostMapping("/addresses")
    public Customer addAddress(
            @RequestParam String userId,
            @RequestBody Address address
    ) {
        return service.addAddress(userId, address);
    }

    @GetMapping("/addresses")
    public List<Address> getAddresses(@RequestParam String userId) {
        return service.getAddresses(userId);
    }

    @DeleteMapping("/addresses/{id}")
    public Customer deleteAddress(
            @RequestParam String userId,
            @PathVariable Long id
    ) {
        return service.deleteAddress(userId, id);
    }

    @PutMapping("/addresses/{id}/default")
    public Customer setDefault(
            @RequestParam String userId,
            @PathVariable Long id
    ) {
        return service.setDefaultAddress(userId, id);
    }
}
