package com.thinkitive.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/ui/**").permitAll()   // allow MVC UI
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated()          // secure all other routes
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // validate Keycloak JWTs
        return http.build();
    }
}