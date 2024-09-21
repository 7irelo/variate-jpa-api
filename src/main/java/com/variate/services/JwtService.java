package com.variate.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;

public interface JwtService {
    String generateToken(String username);
    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
