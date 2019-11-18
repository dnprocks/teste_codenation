package br.com.challenge.repository;

import br.com.challenge.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Users findUsersByToken(String token);

    @Query(value = "select * from users where users.activate = false and not exists(select newuser.id from newuser where newuser.usuario_id = usuario.id)", nativeQuery = true)
    List<Users> findUsersByActiveFalse();
}