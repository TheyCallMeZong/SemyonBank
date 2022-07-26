package com.example.demo.controllers;

import com.example.demo.models.Replenishment;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class OperationController {
    private final Operation operation;

    public OperationController(Operation operation){
        this.operation = operation;
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
        if(operation.transfer(tr)){
            model.addAttribute("user", user);
            return "transfer-success";
        }
        return "err";
    }

    //гетмап для пополнения баланса
    @GetMapping("/lickAmount")
    public String lickAmount(Model model, HttpSession session){
        User user = (User)session.getAttribute("infoUser");
        model.addAttribute("user", new Replenishment());
        return "lickAmount";
    }

    //постмап для пополнения баланса
    @PostMapping ("/lickAmount")
    public String lickAmount(@ModelAttribute("user") Replenishment replenishment, HttpSession session, Model model){
        User user = (User) session.getAttribute("infoUser");
        if (user == null){
            System.err.println("null");
            return "err";
        }
        replenishment.setUser(user);
        operation.lickAmount(replenishment);
        System.out.println(replenishment.getUser());
        model.addAttribute("user", user);
        return "menu";
    }
}
