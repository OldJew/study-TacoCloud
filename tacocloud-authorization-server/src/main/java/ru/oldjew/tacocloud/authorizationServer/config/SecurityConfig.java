package ru.oldjew.tacocloud.authorizationServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.oldjew.tacocloud.authorizationServer.users.UserRepository;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(2)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        http
                .formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> userRepository.findByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
