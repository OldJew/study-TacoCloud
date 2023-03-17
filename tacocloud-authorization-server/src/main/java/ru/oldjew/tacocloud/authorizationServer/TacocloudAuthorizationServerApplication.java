package ru.oldjew.tacocloud.authorizationServer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.oldjew.tacocloud.authorizationServer.users.User;
import ru.oldjew.tacocloud.authorizationServer.users.UserRepository;

@SpringBootApplication

public class TacocloudAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacocloudAuthorizationServerApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(
			UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			userRepository.save(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
			userRepository.save(new User("chef", passwordEncoder.encode("chef"), "ROLE_ADMIN"));
		};
	}
}
