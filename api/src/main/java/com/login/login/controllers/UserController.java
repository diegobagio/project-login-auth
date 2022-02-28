package com.login.login.controllers;

import com.login.login.entities.User;
import com.login.login.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }


    @GetMapping
    public ResponseEntity findAll() { return ok(userService.findAll()); }

    @GetMapping(path = "/id")
    public ResponseEntity findByUsername(String username) {
        return ok(userService.findByUsername(username));
    }

    @PostMapping(path = "/save")
    public ResponseEntity saveUser(@RequestBody User user) {
        return ok(userService.saveUser(user));
    }

    @PostMapping(name = "add-role-user")
    public ResponseEntity addRoleToUser(@RequestBody String username,
                                        @RequestBody String roleName) {
        userService.addRoletoUser(username, roleName);
        return ok().build();
    }

}
