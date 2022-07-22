package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Setter
    @Getter
    private long id;

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

    @Getter
    @Setter
    private double balance;

//TODO: убрать это
    @Override
    public String toString(){
        return "name - " + getName() + " email - " + getEmail() + " phone - " + getPhoneNumber() + " pass " + getPassword();
    }
}
