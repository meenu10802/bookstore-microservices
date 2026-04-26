package com.example.customer_service.service;

import com.example.customer_service.entity.Address;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer getDetails(String userId) {
        return repository.findById(userId).orElse(null);
    }

    public Customer createCustomer(String userId, Customer customer) {
        customer.setUserId(userId);
        return repository.save(customer);
    }

    public Customer updateCustomer(String userId, Customer updated) {
        Customer existing = repository.findById(userId).orElseThrow();

        existing.setPhone(updated.getPhone());
        existing.setPreference(updated.getPreference());

        return repository.save(existing);
    }

    public Customer addAddress(String userId, Address address) {
        Customer customer = repository.findById(userId).orElse(new Customer(userId));

        if (address.isDefault()) {
            customer.getAddresses().forEach(a -> a.setDefault(false));
        }

        customer.getAddresses().add(address);

        return repository.save(customer);
    }

    public List<Address> getAddresses(String userId) {
        Customer customer = repository.findById(userId).orElseThrow();
        return customer.getAddresses();
    }

    public Customer deleteAddress(String userId, Long addressId) {
        Customer customer = repository.findById(userId).orElseThrow();

        customer.getAddresses().removeIf(a -> a.getId().equals(addressId));

        return repository.save(customer);
    }

    public Customer setDefaultAddress(String userId, Long addressId) {
        Customer customer = repository.findById(userId).orElseThrow();

        for (Address a : customer.getAddresses()) {
            a.setDefault(a.getId().equals(addressId));
        }

        return repository.save(customer);
    }
}