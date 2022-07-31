package com.example.demo.repository;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Authentication extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, char[] password);
}
