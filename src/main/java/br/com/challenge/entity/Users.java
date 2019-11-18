package br.com.challenge.entity;

import br.com.challenge.enums.Profile;
import br.com.challenge.listener.UsersListner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Entity
@EntityListeners(UsersListner.class)
public class Users {

    @Transient
    @JsonIgnore
    private String uri;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(length = 80)
    @NotNull
    private String name;

    @Column(length = 80)
    @NotNull
    private String adress;

    @Column(length = 10)
    @NotNull
    private String number;

    @Column(length = 80)
    private String neighborhood;

    @Column(length = 80)
    @NotNull
    private String city;

    @Column(length = 100, unique = true)
    @Email
    @NotNull
    private String email;

    @Column(length = 80)
    @NotNull
    private String password;

    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Profile")
    private Set<Integer> profiles = new HashSet<>();

    public Users() {
        this.active = false;
        addProfile(Profile.USER);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCod());
    }

    @Override
    public String toString() {

        return " Olá! ".concat(name).concat("\n").concat("\n")
                .concat("Obrigado por se registrar em nossa central de erros").concat("\n")
                .concat("Segue suas informações de registro.").concat("\n").concat("\n")
                .concat("Name: ").concat(name).concat("\n")
                .concat("Adress: ").concat(adress).concat("\n")
                .concat("Number: ").concat(number).concat("\n")
                .concat("Neighborhood: ").concat(neighborhood).concat("\n")
                .concat("City: ").concat(city).concat("\n")
                .concat("Email: ").concat(email).concat("\n")
                .concat("Password: ").concat("*********").concat("\n")
                .concat("Profile: ").concat(profiles.toString()).concat("\n").concat("\n").concat("\n")
                .concat("Clique no link abaixo para confirmar sua conta e concluir seu registro.").concat("\n").concat("\n")
                .concat(uri);
    }
}