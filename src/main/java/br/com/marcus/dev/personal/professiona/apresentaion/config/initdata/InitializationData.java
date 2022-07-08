package br.com.marcus.dev.personal.professiona.apresentaion.config.initdata;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.User;
import br.com.marcus.dev.personal.professiona.apresentaion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("dev")
public class InitializationData implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("teste@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("1234567"));
        userRepository.save(user);
    }
}
