package com.svceindore.minor.rest;

import com.svceindore.minor.model.Token;
import com.svceindore.minor.repositories.TokenRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private final TokenRepository tokenRepository;

    public TokenController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/{token}")
    public void updateToken(@PathVariable String token) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Token byEmail = tokenRepository.findByEmail(name);
        if (byEmail==null) {
            byEmail = new Token(name,token);
        }else {
            byEmail.setToken(token);
        }
        tokenRepository.save(byEmail);
    }
}
