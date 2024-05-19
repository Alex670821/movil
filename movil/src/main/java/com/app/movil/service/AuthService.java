package com.app.movil.service;

import com.app.movil.entity.User;
import com.app.movil.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class
AuthService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.info("Username already exists: " + user.getUsername());
            return "Username already exists";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User registered successfully: " + user.getUsername());
        return "User registered successfully";
    }

    public String login(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent() && bCryptPasswordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            logger.info("Login successful for user: " + user.getUsername());
            return "Login successful";
        } else {
            logger.warn("Invalid username or password for user: " + user.getUsername());
            return "Invalid username or password";
        }
    }
}
