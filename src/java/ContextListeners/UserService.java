package teletigers.merch.store.users;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //methods to be done with the users
    public List<User> getUser() {
        return List.of(
                new User("jude","123","admin","j@gmail.com","v,pang")   //replace with database code, just a placeholder
        );
    }
}
