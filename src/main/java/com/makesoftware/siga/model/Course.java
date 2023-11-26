package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    private Integer numberOfSemesters;
    private Integer maxNumbersOfSemestersToFinish;

    @OneToMany
    private List<Subject> subjects;
}
