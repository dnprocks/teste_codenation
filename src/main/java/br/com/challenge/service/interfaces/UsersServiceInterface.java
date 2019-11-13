package br.com.challenge.service.interfaces;

import br.com.challenge.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersServiceInterface {
    List<Users> getUsers();

    Users getUser(Long id);

    Users getUserEmail(String email);

    void saveUsers(Users customer);

    void updateUsers(Users customer);

    void deleteUsers(Long id);

    String activeUsers(Long id);
}
