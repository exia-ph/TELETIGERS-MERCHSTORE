package com.merchstore.services;

import java.util.List;
import com.merchstore.ContextListeners.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //methods to be done with the users (just placeholder methods below)
    public List<User> getUser() {
        return List.of(
                new User("jude","123","admin","j@gmail.com","v,pang")   //replace with database code, just a placeholder
        );
    }
}
