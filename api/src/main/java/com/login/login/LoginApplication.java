package com.login.login;

import com.login.login.entities.Role;
import com.login.login.entities.User;
import com.login.login.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run (UserService userService){
		return  args -> {
//			userService.saveRole(new Role("ADMIN"));
//			userService.saveRole(new Role("USER"));
//			userService.saveRole(new Role("MANAGER"));

//			userService.saveUser(new User("Erica Garcia Bagio", "erica", "erica@gmail.com", "1234"));

//			userService.addRoletoUser("diego", "ADMIN");
//			userService.addRoletoUser("erica", "USER");
		};
	}

}
