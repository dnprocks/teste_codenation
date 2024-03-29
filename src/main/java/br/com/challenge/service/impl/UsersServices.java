package br.com.challenge.service.impl;

import br.com.challenge.entity.Users;
import br.com.challenge.enums.Profile;
import br.com.challenge.exception.AuthorizationException;
import br.com.challenge.exception.NoContentException;
import br.com.challenge.repository.UsersRepository;
import br.com.challenge.security.UserSS;
import br.com.challenge.service.interfaces.EmailServiceInterface;
import br.com.challenge.service.interfaces.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServices implements UsersServiceInterface {


    @Value("${url.user.activite}")
    private String uri;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailServiceInterface emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(Long id) {
        UserSS userSS = UserService.authenticated();

        if (userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())) {
            throw new AuthorizationException("Acesso negado.");
        }

        Optional<Users> users = usersRepository.findById(id);

        if (!users.isPresent()) {
            throw new NoContentException("Usuário não encontrado! Id: " + id + Users.class.getName());
        }
        return users.get();
    }

    @Override
    public Users getUserEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users saveUsers(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setToken(Base64.getEncoder().encodeToString(users.getEmail().getBytes()));
        return usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Users users) {

        Users u = usersRepository.getOne(users.getId());
        if (!u.getPassword().equals(users.getPassword())) {
            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        }

        return usersRepository.save(users);
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public String activeUsers(String token) {
        Users users = usersRepository.findUsersByToken(token);

        if (users != null) {
            users.setActive(true);
            updateUsers(users);

            return "Usuário Registrado com sucesso!";
        }
        return "Erro ao ativar o usuário.";
    }

    @Override
    public List<Users> findUsersByActiveFalse() {
        return usersRepository.findUsersByActiveFalse();
    }
}
