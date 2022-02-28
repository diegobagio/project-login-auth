package com.login.login.controllers;

import com.login.login.entities.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/token")
public class TokenController {

    private final UserDetailsService userDetailsService;

    public TokenController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Value("classpath:rsakey.pem")
    private RSAPrivateKey privateKey;

    @PostMapping
    public String generateToken(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String passworEnconded = encoder.encode(user.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());

        System.out.println(userDetails);

        Instant now = Instant.now();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer("http://localhost:8080")
                .issueTime(new Date(now.toEpochMilli()))
                .expirationTime(new Date(now.plus(10, ChronoUnit.MINUTES).toEpochMilli()))
                .subject(user.getUsername())
                .claim("scope", "ROLE_ADMIN")
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .build();
        SignedJWT jwt = new SignedJWT(header,claims);

        return sign(jwt).serialize();
    }

    private SignedJWT sign(SignedJWT jwt) {
        try {
            jwt.sign(new RSASSASigner(this.privateKey));
            return jwt;
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
