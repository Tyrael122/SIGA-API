package com.makesoftware.siga.util;

import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserUtils {

    public static User findUserByLogin(String login, UserRepository userRepository) {
        User user = userRepository.findByCpf(login);
        if (user == null) user = userRepository.findByEmail(login);

        return user;
    }
}
