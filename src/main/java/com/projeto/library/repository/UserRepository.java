package com.projeto.library.repository;

import com.projeto.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);

    List<User> findAllByName(String name);
}
