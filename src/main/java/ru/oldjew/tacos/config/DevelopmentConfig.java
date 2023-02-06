package ru.oldjew.tacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.oldjew.tacos.model.User;
import ru.oldjew.tacos.repository.UserRepository;

@Configuration
@Profile("dev")
@Slf4j
public class DevelopmentConfig {

    @Bean("devDataLoader")
    public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            log.info("Start load data from dev config...");
            userRepository.save(new User("user", passwordEncoder.encode("user"), "user",
                    "userState", "userCity", "userStreet", "userZIP", "userPhone"));
            log.info("Load finished");
        };
    }
}
