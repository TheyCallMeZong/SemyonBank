package com.example.demo.models.ModelDto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String phoneNumber;
}
