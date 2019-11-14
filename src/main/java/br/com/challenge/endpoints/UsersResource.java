package br.com.challenge.endpoints;

import br.com.challenge.entity.Users;
import br.com.challenge.service.impl.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersResource {

    @Autowired
    private UsersServices usersServices;

    @GetMapping
    public List<Users> getUsers() {
        return usersServices.getUsers();
    }

    @PostMapping
    public Users saveUsers(@RequestBody Users users) {
        return usersServices.saveUsers(users);
    }
}
