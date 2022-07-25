package com.example.demo.service;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService implements Authentication{

    //TODO: раскидать по разным файлам методы, а то сейчас мне лень
    //TODO: добавить коннект к бд
    private final List<User> users;

    public AuthenticationService(){
        users = new ArrayList<>();
        User admin = new User();
        admin.setName("e");
        admin.setPersonalAccount(1);
        users.add(admin);
    }

    @Override
    public boolean auth(@NotNull UserAuthorization userAuth) {
        var result = users.stream().filter(x -> Objects.equals(x.getEmail(), userAuth.getEmail())
                && Objects.equals(x.getPassword(), userAuth.getPassword())).findAny().orElse(null);
        return result != null; //возвращем false если пользователь еще не был зарегестрирован, иначе true
    }

    @Override
    public boolean registration(User user) {
        var result = users.stream().filter(x -> Objects.equals(x.getEmail(), user.getEmail())).findAny()
                .orElse(null);

        if (result == null){ //если пользователь не был зарегестрирован, то регестрируем и возвращаем true
            user.setBalance(100);
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Transaction transaction) {
        var toUser = users.stream().filter(x -> x.getPersonalAccount() == transaction.getToUserPersonalAccount())
                .findAny().orElse(null);

        if (toUser == null) {
            return false;
        }
        var fromUser = users.stream().filter(x -> x.getPersonalAccount() == transaction.getFromUserPersonalAccount())
                .findAny().orElse(null);

        if (fromUser != null && fromUser.getBalance() > transaction.getSum()) {
            fromUser.setBalance(fromUser.getBalance() - transaction.getSum());
            toUser.setBalance(toUser.getBalance() + transaction.getSum());
            return true;
        }
        return false;
    }
}
