package br.com.marcus.dev.personal.professional.apresentation.config.security;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSS implements UserDetails {

    private UUID uuid;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(UUID uuid, String email, String password, Set<Profile> profiles) {
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toSet());
    }

    public UUID getId(){
        return this.uuid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
