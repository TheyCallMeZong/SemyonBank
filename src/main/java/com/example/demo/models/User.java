package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private char[] password;

    @Getter
    @Setter
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Getter
    @Setter
    private double balance;

    @Getter
    @Setter
    @Column(name = "personalaccount")
    private int personalAccount;

//TODO: убрать это
    @Override
    public String toString(){
        return "id - " + getId() + "name - " + getName() + " email - " + getEmail() + " phone - " + getPhoneNumber() + " pass " + getPassword();
    }
}
