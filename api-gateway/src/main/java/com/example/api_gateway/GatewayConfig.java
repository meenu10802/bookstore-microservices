package com.example.api_gateway;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    // GET /api/users/profile
    // PUT /api/users/profile
    // PUT /api/users/change-password
    // DELETE /api/users/{id}
    // GET /api/users/health
    // POST /api/users/register
    // POST /api/users/login
    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user-service")
                .GET("/api/users/**", HandlerFunctions.http())
                .POST("/api/users/**", HandlerFunctions.http())
                .PUT("/api/users/**", HandlerFunctions.http())
                .DELETE("/api/users/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("user-service"))
                .build();
    }

    // GET /api/admin/all-users
    // POST /api/admin/register
    // GET /api/admin/orders
    // PUT /api/admin/orders/{id}/status
    // PUT /api/admin/products/{id}
    // DELETE /api/admin/products/{id}
    @Bean
    public RouterFunction<ServerResponse> adminServiceRoute() {
        return GatewayRouterFunctions.route("admin-service")
                .GET("/api/admin/**", HandlerFunctions.http())
                .POST("/api/admin/**", HandlerFunctions.http())
                .PUT("/api/admin/**", HandlerFunctions.http())
                .DELETE("/api/admin/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("admin-service"))
                .build();
    }

    // POST /api/products
    // GET /api/products
    // GET /api/products/{id}
    // PUT /api/products/{id}
    // DELETE /api/products/{id}
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product-service")
                .GET("/api/products/**", HandlerFunctions.http())
                .POST("/api/products/**", HandlerFunctions.http())
                .PUT("/api/products/**", HandlerFunctions.http())
                .DELETE("/api/products/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("product-service"))
                .build();
    }

    // GET /api/cart/{userId}
    // POST /api/cart/add
    // PUT /api/cart/update
    // DELETE /api/cart/remove/{productId}
    // DELETE /api/cart/clear
    // GET /api/cart/total
    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute() {
        return GatewayRouterFunctions.route("cart-service")
                .GET("/api/cart/**", HandlerFunctions.http())
                .POST("/api/cart/**", HandlerFunctions.http())
                .PUT("/api/cart/**", HandlerFunctions.http())
                .DELETE("/api/cart/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("cart-service"))
                .build();
    }

    // GET /api/wishlist
    // POST /api/wishlist/add/{productId}
    // DELETE /api/wishlist/remove/{productId}
    // DELETE /api/wishlist/clear
    @Bean
    public RouterFunction<ServerResponse> wishlistServiceRoute() {
        return GatewayRouterFunctions.route("wishlist-service")
                .GET("/api/wishlist/**", HandlerFunctions.http())
                .POST("/api/wishlist/**", HandlerFunctions.http())
                .DELETE("/api/wishlist/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("wishlist-service"))
                .build();
    }

    // GET /api/customers/details
    // POST /api/customers/details
    // PUT /api/customers/details
    // POST /api/customers/addresses
    // GET /api/customers/addresses
    // DELETE /api/customers/addresses/{id}
    // PUT /api/customers/addresses/{id}/default
    @Bean
    public RouterFunction<ServerResponse> customerServiceRoute() {
        return GatewayRouterFunctions.route("customer-service")
                .GET("/api/customers/**", HandlerFunctions.http())
                .POST("/api/customers/**", HandlerFunctions.http())
                .PUT("/api/customers/**", HandlerFunctions.http())
                .DELETE("/api/customers/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("customer-service"))
                .build();
    }

    // POST /api/orders/place
    // GET /api/orders/{id}
    // GET /api/orders
    // PUT /api/orders/{id}/status
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order-service")
                .GET("/api/orders/**", HandlerFunctions.http())
                .POST("/api/orders/**", HandlerFunctions.http())
                .PUT("/api/orders/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("order-service"))
                .build();
    }

    // POST /api/feedback
    // GET /api/feedback/product/{id}
    // GET /api/feedback/product/{id}/rating
    // PUT /api/feedback/{id}
    // DELETE /api/feedback/{id}
    @Bean
    public RouterFunction<ServerResponse> feedbackServiceRoute() {
        return GatewayRouterFunctions.route("feedback-service")
                .GET("/api/feedback/**", HandlerFunctions.http())
                .POST("/api/feedback/**", HandlerFunctions.http())
                .PUT("/api/feedback/**", HandlerFunctions.http())
                .DELETE("/api/feedback/**", HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("feedback-service"))
                .build();
    }
}