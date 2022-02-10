package com.login.login.controllers;

import com.login.login.entities.User;
import com.login.login.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() { return userService.findAll(); }

   @PostMapping("pre-authorized")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<User> postPreAuthorizedUser(@PathVariable User user,
                                                     @AuthenticationPrincipal UserDetails details) {
        log.info(details);
        return ok(userService.postPreAuthorizedUser(user));
   }
}
