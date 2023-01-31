package ru.oldjew.tacos.Utils;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.oldjew.tacos.model.User;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String state;
    private String city;
    private String street;
    private String zip;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(
                username, passwordEncoder.encode(password), fullname,
                state, city, street, zip, phoneNumber);
    }
}
