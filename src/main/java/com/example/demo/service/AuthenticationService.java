package com.example.demo.service;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.ModelDto.UserDto;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService implements Authentication{

    private final List<User> users;

    public AuthenticationService(){
        users = new ArrayList<>();
    }

    @Override
    public boolean auth(UserAuthorization userAuth) {
        var result = users.stream().filter(x -> Objects.equals(x.getEmail(), userAuth.getEmail())
                && Objects.equals(x.getPassword(), userAuth.getPassword())).findAny().orElse(null);
        return result != null; //возвращем false если пользователь еще не был зарегестрирован, иначе true
    }

    @Override
    public boolean registration(UserDto user) {
        var result = users.stream().filter(x -> Objects.equals(x.getEmail(), user.getEmail())).findAny().orElse(null);
        if (result == null){ //если пользователь не был зарегестрирован, то регестрируем и возвращаем true
            addNewUser(user);
            return true;
        }
        return false;
    }

    private void addNewUser(UserDto user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setId(users.size());
        newUser.setBalance(0);
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(user.getPassword());
        users.add(newUser);
        System.out.println(newUser);
    }
}
