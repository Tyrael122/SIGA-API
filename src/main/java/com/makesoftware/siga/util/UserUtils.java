package com.makesoftware.siga.util;

import com.makesoftware.siga.model.Role;
import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserUtils {

    public static User findUserByLogin(String login, UserRepository userRepository) {
        User user = userRepository.findByCpf(login);
        if (user == null) user = userRepository.findByEmail(login);

        return user;
    }

    public static void addRoleToUser(User user, Role roleToAdd) {
        List<Role> userRoles = user.getRoles();
        userRoles.add(roleToAdd);

        user.setRoles(userRoles);
    }

    public static void removeRoleFromUser(User user, Role roleToRemove) {
        List<Role> userRoles = user.getRoles();
        userRoles.remove(roleToRemove);

        user.setRoles(userRoles);
    }
}
