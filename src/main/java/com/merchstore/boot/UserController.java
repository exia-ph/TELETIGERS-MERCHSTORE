package com.merchstore.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
public class UserController
{
    String header = "TigerNex";
    String footer = "All Rights Reserved 2022";

    @RequestMapping("/")
    public String welcome(Model model)
    {
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
        return "welcome";
    }

    @RequestMapping("/store")
    public String store(Model model)
    {
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
        return "store";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
        return "login";
    }

    //may separate to its own controller
    @PostMapping("/check")
    public String checker(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
        //hardcoded credentials: user=boi pass=boi
        if(username.equals("") && password.equals("")) return "errors/noLoginCredentials";
        if(username.equals("boi")) if(password.equals("boi")) return "loggedin/storeUser";
        return "errors/error_login";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus status)
    {
        status.setComplete();
            
        //Redirect
        return "login";
    }

    @PostMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
        return "login";
    }
}
