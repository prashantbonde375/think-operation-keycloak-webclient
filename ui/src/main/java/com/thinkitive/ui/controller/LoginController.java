package com.thinkitive.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // @GetMapping("/")
    // public String home(@AuthenticationPrincipal OidcUser user, Model model) {
    //     model.addAttribute("name", user != null ? user.getFullName() : "Guest");
    //     return "home"; // create home.html if needed
    // }

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/keycloak";
    }

    @GetMapping("/register")
    public String register() {
        // Redirect to Keycloak registration page directly
        return "redirect:http://localhost:8080/realms/<your-realm-name>/protocol/openid-connect/registrations";
    }
}
