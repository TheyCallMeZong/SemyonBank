package com.example.demo.repository;

import com.example.demo.models.Replenishment;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Operation extends JpaRepository<User, Integer> {
    User findByPersonalAccount(int personalAccount);
}
