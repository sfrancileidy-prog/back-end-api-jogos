package com.backend.apiJogos.config.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public String getSupabaseUserId(Jwt jwt) {
        return jwt.getSubject();
    }

    public String getEmail(Jwt jwt) {
        return jwt.getClaimAsString("email");
    }
}
