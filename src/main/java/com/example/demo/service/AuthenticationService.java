package com.example.demo.service;

import com.example.demo.UsersRepository;
import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService implements Authentication{

    public AuthenticationService(){
        User admin = new User();
        admin.setName("Semyon");
        admin.setEmail("savhenko10@mail.ru");
        //TODO: точно убрать! но пока для тестов
        admin.setPassword(new char[] {'p', 'a', 's', 's'});
        admin.setPersonalAccount(1);
        UsersRepository.USER_LIST.add(admin);
    }

    @Override
    public User auth(@NotNull UserAuthorization userAuth) {
        return UsersRepository.USER_LIST.stream().filter(x -> Objects.equals(x.getEmail(), userAuth.getEmail())
                && Arrays.equals(x.getPassword(), userAuth.getPassword())).findAny().orElse(null);
    }

    @Override
    public boolean registration(User user) {
        var result = UsersRepository.USER_LIST.stream().filter(x -> Objects.equals(x.getEmail(), user.getEmail())).findAny()
                .orElse(null);

        if (result == null){ //если пользователь не был зарегестрирован, то регестрируем и возвращаем true
            user.setBalance(100);
            UsersRepository.USER_LIST.add(user);
            return true;
        }
        return false;
    }
}
