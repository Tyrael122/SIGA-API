package com.makesoftware.siga.controller;

import com.makesoftware.siga.security.AuthenticationData;
import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.UserRepository;
import com.makesoftware.siga.security.AuthenticationResponse;
import com.makesoftware.siga.security.TokenService;
import com.makesoftware.siga.util.Messages;
import com.makesoftware.siga.util.ControllerUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final String ENDPOINT_PREFIX = "/users";

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping(ENDPOINT_PREFIX + "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationData user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var authentication = authenticationManager.authenticate(usernamePassword);

        if (!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Messages.INVALID_CREDENTIALS.getMessage());
        }

        String token = tokenService.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok().body(new AuthenticationResponse(token));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(ENDPOINT_PREFIX)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        validateUserDoesntExist(user);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(ENDPOINT_PREFIX + "/{id}")
    public User getUserById(@PathVariable long id) {
        return ControllerUtils.findById(id, userRepository, Messages.USER_NOT_FOUND);
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        return ControllerUtils.deleteById(id, userRepository, Messages.USER_DELETED);
    }

    private void validateUserDoesntExist(User user) {
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Messages.CPF_ALREADY_EXISTS.getMessage());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Messages.EMAIL_ALREADY_EXISTS.getMessage());
        }
    }
}
