package com.makesoftware.siga.model;

import com.makesoftware.siga.util.Messages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank()
    @Column(unique = true)
    @Size(max = 11)
    private String cpf;
    private String rg;

    private String firstName;
    private String lastName;

    @NotBlank()
    @Column(unique = true)
    private String email;

    @NotBlank()
    @Size(min = 8, message = Messages.PASSWORD_MIN_SIZE)
    private String password;

    private LocalDateTime birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
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
