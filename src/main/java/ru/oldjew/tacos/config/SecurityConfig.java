package ru.oldjew.tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.oldjew.tacos.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.oldjew.tacos.repository.UserRepository;

@Configuration
public class SecurityConfig {

    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                .antMatchers("/design","/orders*").hasRole("USER")
                .antMatchers("/h2-console/**", "/","/**", "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/ingredients").
                    hasAuthority("SCOPE_writeIngredients")
                .antMatchers(HttpMethod.DELETE, "/api/ingredients")
                    .hasAuthority("SCOPE_deleteIngredients")
                .and()
                .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/", true)
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .build();
    }
}
