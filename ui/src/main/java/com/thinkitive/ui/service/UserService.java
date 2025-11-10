package com.thinkitive.ui.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient.Builder webClientBuilder;
    private final String gatewayBaseUrl;

    public UserService(WebClient.Builder webClientBuilder,
            @Value("${app.gateway.base-url:http://localhost:8080}") String gatewayBaseUrl) {
        this.webClientBuilder = webClientBuilder;
        this.gatewayBaseUrl = gatewayBaseUrl;
    }

    public void handleUserLogin(OidcUser oidcUser) {
        if (oidcUser == null) {
            System.out.println("❌ OidcUser is null");
            return;
        }

        String keycloakId = oidcUser.getSubject();
        String username = oidcUser.getPreferredUsername();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String token = oidcUser.getIdToken().getTokenValue();

        System.out.printf("➡️ Syncing user: %s (%s)%n", name, email);

        // ✅ Always store user in HR service
        String targetUrl = gatewayBaseUrl + "/hr/add";

        Map<String, Object> request = Map.of(
                "keycloakId", keycloakId,
                "username", username,
                "email", email,
                "name", name);

        webClientBuilder.build()
                .post()
                .uri(targetUrl)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), Map.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(err -> System.out.println("❌ Failed to sync user: " + err.getMessage()))
                .subscribe(response -> System.out
                        .println("✅ User synced successfully via Gateway → HR Service: " + response));
    }
}
