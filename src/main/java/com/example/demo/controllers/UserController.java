package com.example.demo.controllers;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final Authentication service;

    public UserController(Authentication authentication){
        service = authentication;
    }

    //гетмап регистрации
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userRegistration", new User());
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
    public String registration(@RequestParam("repeatPassword") String password,
                               @ModelAttribute User user,
                               Model model,
                               HttpSession session){
        if (!password.equals(user.getPassword())){
            System.out.println(password + " " + user.getPassword());
            return "err";
        }

        if (service.registration(user)){
            System.out.println("add new User");
            user.setPersonalAccount((int) (Math.random() * 1000));
            session.setAttribute("infoUser", user);
            model.addAttribute("user", user);
        } else {
            return "err";
        }
        return "menu";
    }

    @GetMapping("/menu")
    public String menu(Model model, HttpSession session) {
        User user = (User) session.getAttribute("infoUser");
        model.addAttribute("user", user);
        return "menu";
    }

    @GetMapping("/transfer")
    public String transfer(Model model){
        model.addAttribute("transfer", new Transaction());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute Transaction tr, Model model, HttpSession session){
        User user = (User) session.getAttribute("infoUser");
        tr.setFromUserPersonalAccount(user.getPersonalAccount());
        System.out.println(tr.getFromUserPersonalAccount() + " " + tr.getToUserPersonalAccount());
        if(service.transfer(tr)){
            model.addAttribute("user", user);
            return "transfer-success";
        }
        return "err";
    }
}
