package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User{

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private String userCreation;
    private String userUpdate;
    private boolean status;
    private String name;
    private String email;
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_PROFILE")
    private Set<Integer> profiles = new HashSet<>();

    public User() {
        uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        dateCreation = now;
        status = true;
    }

    public User(UUID uuid, LocalDateTime dateCreation, String userCreation, boolean status, String name, String email) {
        this.uuid = uuid;
        this.dateCreation = dateCreation;
        this.userCreation = userCreation;
        this.status = status;
        this.name = name;
        this.email = email;
    }

    public User(UUID uuid, LocalDateTime dateCreation, LocalDateTime dateUpdate, String userCreation, String userUpdate, boolean status, String name, String email, String password) {
        this.uuid = uuid;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.userCreation = userCreation;
        this.userUpdate = userUpdate;
        this.status = status;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getNumber());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }
}
