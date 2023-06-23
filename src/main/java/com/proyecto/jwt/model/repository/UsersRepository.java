package com.proyecto.jwt.model.repository;

import com.proyecto.jwt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUserByUsername(String username);
    Users findUserByUsernameAndPassword(String username, String password);
}
