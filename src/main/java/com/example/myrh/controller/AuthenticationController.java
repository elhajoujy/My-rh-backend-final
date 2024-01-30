package com.example.myrh.controller;

import com.example.myrh.dto.MessageDto;
import com.example.myrh.dto.TokenDto;
import com.example.myrh.dto.UrlDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class AuthenticationController {


    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    @GetMapping("/mySession")
    public Authentication getauthentication(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/auth/url")
    public ResponseEntity<UrlDto> auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId, "http://locahost:4200", Arrays.asList("profile", "email", "openid")

        ).build();
        return ResponseEntity.ok(new UrlDto(url));
    }

    @GetMapping("/auth/callback")
    public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) {
        String token;
        try {
            token = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new GsonFactory(), clientId, clientSecret, code, "http://locahost:4200").execute().getAccessToken();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new TokenDto(token));
    }


    @GetMapping("/message")
    public String privateMessage(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        return "response ";
    }

    @GetMapping("/private/message")
    public ResponseEntity<MessageDto> privateMessage(@AuthenticationPrincipal(expression = "name") String name

    ) {

        return ResponseEntity.ok(new MessageDto("private Content" + name));
    }


}
