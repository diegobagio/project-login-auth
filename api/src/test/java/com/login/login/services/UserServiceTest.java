package com.login.login.services;

import com.login.login.entities.Role;
import com.login.login.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService service;

    @Test
    void findAll() {
        service.findAll();
    }

    @Test
    void findByUsername() {
        service.findByUsername("diego");
    }

    @Test
    void saveUser() {
        service.saveUser(new User("Diego", "diego", "diego@gmail.com", "test123"));
    }

    @Test
    void saveRole() {
        service.saveRole(new Role("ADMIN"));
    }

    @Test
    void addRoletoUser() {
        service.addRoletoUser("diego", "ADMIN");
    }
}