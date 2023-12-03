package com.makesoftware.siga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

import static com.makesoftware.siga.util.Defaults.DEFAULT_MANDATORY_MESSAGE;

@Data
@Entity
public class User {

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
}
