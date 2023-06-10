package ru.gtatarnikov.tacocloud.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gtatarnikov.tacocloud.entity.Client;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public Client toClient(PasswordEncoder encoder) {
        return new Client(
                username, encoder.encode(password)
                , fullname, street, city, state, zip, phoneNumber);
    }
}