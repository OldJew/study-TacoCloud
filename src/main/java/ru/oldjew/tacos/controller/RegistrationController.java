package ru.oldjew.tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oldjew.tacos.Utils.RegistrationForm;
import ru.oldjew.tacos.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
        log.info("user registered: " + form.toUser(passwordEncoder).toString());
        return "redirect:/login";
    }
}
