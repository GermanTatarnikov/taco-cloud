package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gtatarnikov.tacocloud.dto.RegistrationForm;
import ru.gtatarnikov.tacocloud.repository.ClientRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        clientRepository.save(form.toClient(passwordEncoder));
        return "redirect:/login";
    }
}