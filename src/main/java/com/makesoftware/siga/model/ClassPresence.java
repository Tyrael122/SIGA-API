package com.makesoftware.siga.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ClassPresence {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Student student;
    private Boolean isPresent;
}
