package com.merchstore.checker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
public class UserController
{

    @PostMapping("login")
    public String checker(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        //insert database connection service here
        //insert user verification here
        //bind user object to session attribute here
        return "loggedin/storeUser";
    }

    @PostMapping("logout")
    public String logout(SessionStatus status)
    {
        status.setComplete();
            
        //Redirect
        return "login";
    }

    @PostMapping("register")
    public String register()
    {
        //
        return "login";
    }
}
