package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    User findByCpf(String cpf);
    User findByEmail(String email);
}
