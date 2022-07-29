package com.example.demo.service;

import com.example.demo.UsersRepository;
import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService{
    private final Authentication authentication;

    @Autowired
    public AuthenticationService(Authentication authentication){
        User admin = new User();
        admin.setName("Semyon");
        admin.setEmail("savhenko10@mail.ru");
        //TODO: точно убрать! но пока для тестов
        admin.setPassword(new char[] {'p', 'a', 's', 's'});
        admin.setPersonalAccount(1);
        UsersRepository.USER_LIST.add(admin);
        this.authentication = authentication;
    }

    public User auth(@NotNull UserAuthorization userAuth) {
        return UsersRepository.USER_LIST.stream().filter(x -> Objects.equals(x.getEmail(), userAuth.getEmail())
                && Arrays.equals(x.getPassword(), userAuth.getPassword())).findAny().orElse(null);
    }

    public boolean registration(User user) {
        var result = UsersRepository.USER_LIST.stream().filter(x -> Objects.equals(x.getEmail(), user.getEmail())).findAny()
                .orElse(null);

        if (result == null){ //если пользователь не был зарегестрирован, то регестрируем и возвращаем true
            user.setBalance(100);
            UsersRepository.USER_LIST.add(user);
            System.err.println(user);
            authentication.save(user);
            return true;
        }
        return false;
    }
}
