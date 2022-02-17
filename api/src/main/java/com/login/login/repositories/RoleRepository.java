package com.login.login.repositories;

import com.login.login.entities.Role;
import com.login.login.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, Long>, JpaRepository<Role, Long> {
    Role findByName(String name);
}
