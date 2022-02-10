package com.login.login.repositories;

import com.login.login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long>, JpaRepository<User, Long> {
    User findByUsername(String username);
}
