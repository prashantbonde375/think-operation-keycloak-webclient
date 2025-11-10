package com.thinkitive.ui.controller;

import com.thinkitive.ui.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private final UserService userService;

    LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/keycloak";
    }

    @GetMapping("/logout")
    public String logout() {
        // Redirect to Keycloak's logout endpoint
        return "redirect:http://localhost:9090/realms/thinkitive/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/home";
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OidcUser oidcUser) {
        userService.handleUserLogin(oidcUser);
        return "redirect:/home";
    }

    @GetMapping("/token")
    @ResponseBody
    public String getToken(
            @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser) {

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        String refreshToken = authorizedClient.getRefreshToken() != null
                ? authorizedClient.getRefreshToken().getTokenValue()
                : "No refresh token available";

        return """
                Access Token: %s
                Refresh Token: %s
                User: %s
                """.formatted(accessToken, refreshToken, oidcUser.getEmail());
    }
}