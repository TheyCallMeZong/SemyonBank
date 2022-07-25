package com.example.demo.repository;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface Authentication {
    boolean auth(UserAuthorization user);

    boolean registration(User user);

    boolean transfer(Transaction transaction);
}
