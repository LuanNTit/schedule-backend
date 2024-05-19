package com.example.microservicegateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service_auth_route", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f
//                                .addRequestHeader("Authorization", "Bearer {token}")
                                .rewritePath("/api/auth/(?<segment>.*)", "/$\\{segment}"))
                        .uri("http://localhost:8081"))
                .build();
    }
}
