package com.makesoftware.siga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String acronym;

    @NotBlank
    private String name;
    private String description;

    private Integer numberOfSemesters;
    private Integer maxNumbersOfSemestersToFinish;

    @ManyToMany
    private List<Subject> subjects;
}
