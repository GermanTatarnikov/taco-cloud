package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gtatarnikov.tacocloud.dto.RegistrationForm;
import ru.gtatarnikov.tacocloud.service.RegistrationService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registerForm() {
        return registrationService.registerForm();
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        return registrationService.processRegistration(form);
    }
}