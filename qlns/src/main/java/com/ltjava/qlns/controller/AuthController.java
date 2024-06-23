package com.ltjava.qlns.controller;



import com.ltjava.qlns.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/home")
    public String test(){
        return "home";
    }

    @GetMapping("/403")
    public String accesdenied(){
        return "403";
    }
}
