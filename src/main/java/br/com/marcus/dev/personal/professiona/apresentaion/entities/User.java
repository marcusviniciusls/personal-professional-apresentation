package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    @CollectionTable(name = "PROFILE")
    private Set<Integer> profiles = new HashSet<>();

    public Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getNumber());
    }
}
