package com.example.demo.controllers;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.ModelDto.UserDto;
import com.example.demo.repository.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private Authentication service;

    public UserController(Authentication authentication){
        service = authentication;
    }

    //гетмап регистрации
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userReg", new UserDto());
        return "registration";
    }

    //гетмап авторизаии
    @GetMapping("/authorization")
    public String auth(Model model){
        model.addAttribute("userAuth",new UserAuthorization());
        return "authorization";
    }

    //TODO: спринг секьюрити изучить
    //постмап регистрации
    @PostMapping("/registration")
    public String registration(@RequestParam("repeatPassword") String password, @ModelAttribute UserDto user, Model model){
        if (!password.equals(user.getPassword())){
            System.out.println(password + " " + user.getPassword());
            return "err";
        }
        if (service.registration(user)){
            System.out.println("add new User");
            model.addAttribute("user", user);
        } else {
            return "err";
        }
        return "menu";
    }
}
