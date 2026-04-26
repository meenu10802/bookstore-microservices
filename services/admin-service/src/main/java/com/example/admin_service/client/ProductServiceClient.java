package com.example.admin_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//this let one service (admin service) talk to another service (product service) easily
@FeignClient(name = "product-service", path = "/api/products")//It acts like a remote API caller
//Instead of writing HTTP calls manually, Spring does it for you
public interface ProductServiceClient {
//inside this write what admin needs to do with product from product-services
    @PutMapping("/{id}")
    Object updateProduct(@PathVariable Long id, @RequestBody Object product);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id);
}
/*In microservices:
Admin Service handles admin operations
Product Service manages products

👉 But admin needs to:
Update products
Delete products

So it must call Product Service APIs Feign helps do that cleanly*/