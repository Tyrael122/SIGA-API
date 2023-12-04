package com.makesoftware.siga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.makesoftware.siga.util.Defaults.DEFAULT_MANDATORY_MESSAGE;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "CPF" + DEFAULT_MANDATORY_MESSAGE)
    @Column(unique = true)
    @Size(max = 11, message = "CPF deve ter no máximo 11 caracteres")
    private String cpf;
    private String rg;

    private String firstName;
    private String lastName;

    @NotBlank(message = "Email" + DEFAULT_MANDATORY_MESSAGE)
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Senha" + DEFAULT_MANDATORY_MESSAGE)
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;

    private LocalDateTime birthDate;

    @Enumerated
    @ElementCollection
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
