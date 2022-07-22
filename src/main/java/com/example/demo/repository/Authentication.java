package com.example.demo.repository;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.ModelDto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface Authentication {
    boolean auth(UserAuthorization user);

    boolean registration(UserDto user);
}
