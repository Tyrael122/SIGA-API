package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String cpf;
    private String rg;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    private LocalDateTime birthDate;
}
