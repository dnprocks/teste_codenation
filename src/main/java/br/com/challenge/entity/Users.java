package br.com.challenge.entity;

import br.com.challenge.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Entity
public class Users {
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
}