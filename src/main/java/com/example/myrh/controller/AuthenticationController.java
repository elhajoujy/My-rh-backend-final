package com.example.myrh.controller;

import com.example.myrh.dto.MessageDto;
import com.example.myrh.dto.TokenDto;
import com.example.myrh.dto.UrlDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Arrays;
import java.util.logging.Logger;

@RestController
@Slf4j
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
        String url = new GoogleAuthorizationCodeRequestUrl(clientId, "http://localhost:4200", Arrays.asList("profile", "email", "openid")).build();
        return ResponseEntity.ok(new UrlDto(url));
    }

    @GetMapping("/auth/callback")
    public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) throws URISyntaxException {

        String token;
        try {
            token = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new GsonFactory(), clientId, clientSecret, code, "http://localhost:4200").execute().getAccessToken();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(new TokenDto(token));
    }

    @GetMapping("/message")
    public String privateMessage(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        return "response ";
    }

    @GetMapping("/private/message")
    public ResponseEntity<Authentication> privateMessage(Authentication authentication) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}", authentication.getPrincipal());
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/private/messages")
    public ResponseEntity<Object> privateMessages(@AuthenticationPrincipal OAuth2User principal, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}", principal.toString());
        log.info("authentication : {}", authentication.getCredentials().toString());
        return ResponseEntity.ok(principal);
    }

}
