package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final String ENDPOINT_PREFIX = "/users";
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(ENDPOINT_PREFIX)
    public User create(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
