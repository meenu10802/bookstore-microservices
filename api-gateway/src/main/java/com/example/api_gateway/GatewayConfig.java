package com.example.api_gateway;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/users/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("user-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> adminServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/admin/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("admin-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/products/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("product-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/cart/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("cart-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> wishlistServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/wishlist/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("wishlist-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> customerServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/customers/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("customer-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/orders/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("order-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> feedbackServiceRoute() {
        return GatewayRouterFunctions.route()
                .route(path("/api/feedback/**"),
                        HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("feedback-service"))
                .build();
    }
}