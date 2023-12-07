package com.makesoftware.siga.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String urlCurriculoLattes;
}
