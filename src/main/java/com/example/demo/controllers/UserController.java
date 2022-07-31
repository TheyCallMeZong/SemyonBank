package com.example.demo.controllers;

import com.example.demo.models.Authorization.UserAuthorization;
import com.example.demo.models.User;
import com.example.demo.repository.Authentication;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class UserController {
    private final AuthenticationService service;

    @Autowired
    public UserController(AuthenticationService authentication){
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

    @PostMapping("/authorization")
    public String auth(@ModelAttribute UserAuthorization userAuthorization, Model model, HttpSession session){
        User user = service.auth(userAuthorization);
        if (user == null){
            return "err";
        }
        model.addAttribute("user", user);
        session.setAttribute("infoUser", user);
        return "menu";
    }

    //TODO: спринг секьюрити изучить
    //постмап регистрации
    @PostMapping("/registration")
    public String registration(@RequestParam("repeatPassword") char[] password,
                               @ModelAttribute User user,
                               Model model,
                               HttpSession session){
        if (!Arrays.equals(password, user.getPassword())){
            System.out.println(Arrays.toString(password) + " " + Arrays.toString(user.getPassword()));
            return "err";
        }
        if (service.registration(user)){
            System.out.println("add new User");
            session.setAttribute("infoUser", user);
            model.addAttribute("user", user);
            return "menu";
        } else {
            return "err";
        }
    }

    //гетмап менюшки
    @GetMapping("/menu")
    public String menu(Model model, HttpSession session) {
        User user = (User) session.getAttribute("infoUser");
        model.addAttribute("user", user);
        return "menu";
    }
}
