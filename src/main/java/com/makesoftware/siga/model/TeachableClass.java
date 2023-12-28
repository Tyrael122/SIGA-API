package com.makesoftware.siga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class TeachableClass {
    @Id
    @GeneratedValue
    private Long id;

    private String subject;

    @NotNull
    private LocalDate date;

    @ManyToOne
    private ClassTimeBlock classTimeBlock;

    @OneToOne
    private ClassPresenceList presenceList;
}
