package br.com.marcus.dev.personal.professiona.apresentaion.config.security;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.User;
import br.com.marcus.dev.personal.professiona.apresentaion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
        return new UserSS(user.getUuid(), user.getEmail(), user.getPassword(), user.getProfiles());
    }
}
