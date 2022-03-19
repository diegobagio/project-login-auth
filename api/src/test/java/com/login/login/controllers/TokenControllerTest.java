package com.login.login.controllers;

import com.login.login.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenControllerTest {

    private TokenController controller;

    @Test
    void generateToken() {
        final var user = new User();
        user.setUsername("diego");
        user.setPassword("test123");

        controller.generateToken(user);
    }
}