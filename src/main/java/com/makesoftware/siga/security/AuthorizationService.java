package com.makesoftware.siga.security;

import com.makesoftware.siga.repository.UserRepository;
import com.makesoftware.siga.util.UserUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails userDetails = UserUtils.findUserByLogin(login, userRepository);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDetails;
    }
}
