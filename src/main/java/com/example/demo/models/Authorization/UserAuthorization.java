package com.example.demo.models.Authorization;

import lombok.Getter;
import lombok.Setter;

public class UserAuthorization {
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;
}
