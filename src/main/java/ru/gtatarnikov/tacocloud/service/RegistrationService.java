package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gtatarnikov.tacocloud.model.dto.RegistrationForm;
import ru.gtatarnikov.tacocloud.model.entity.User;
import ru.gtatarnikov.tacocloud.repository.UserRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerForm() {
        return "registration";
    }

    @Transactional
    public String processRegistration(RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        userRepository.save(user);
        log.info("User with id {} saved", user.getId());
        return "redirect:/login";
    }
}
