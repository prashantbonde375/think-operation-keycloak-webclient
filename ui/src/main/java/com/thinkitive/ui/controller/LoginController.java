package com.thinkitive.ui.controller;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/profile")
    public String profile(Model model, OAuth2AuthenticationToken authentication) {
        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
        model.addAttribute("user", attributes);
        return "profile";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/token")
    @ResponseBody
    public String token(OAuth2AuthenticationToken authentication) {
        var principal = authentication.getPrincipal();
        var token = principal.getAttribute("access_token");
        return "Access Token: " + token;
    }
}