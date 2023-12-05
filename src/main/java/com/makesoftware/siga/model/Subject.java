package com.makesoftware.siga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private String description;
    private Integer workload;

    private Integer defaultSemester;

    @ManyToOne
    private Course course;

    @OneToMany
    private List<TeachableSubject> teachableSubjects;
}
