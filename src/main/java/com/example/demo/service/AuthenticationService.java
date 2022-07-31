package com.example.demo.service;

import com.example.demo.UsersRepository;
import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Objects;

@Service
public class AuthenticationService {
    private final Authentication authentication;

    @Autowired
    public AuthenticationService(Authentication authentication){
        this.authentication = authentication;
    }

    public User auth(@NotNull UserAuthorization userAuth) {
        return authentication.findByEmailAndPassword(userAuth.getEmail(), userAuth.getPassword());
    }

    public boolean registration(User user) {
        User userDb = authentication.findByEmail(user.getEmail());
        if (userDb != null){
            return false;
        }
        user.setPersonalAccount((int) (Math.random() * 1000));
        user.setBalance(100);
        System.err.println(user);
        authentication.save(user);
        return true;
    }
}
