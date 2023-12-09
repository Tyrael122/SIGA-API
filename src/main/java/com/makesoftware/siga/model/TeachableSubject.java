package com.makesoftware.siga.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class TeachableSubject {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Subject basisSubject;

    @ManyToOne
    private Teacher teacher;

    @OneToMany
    private List<ClassTimeBlock> schedule;

    @OneToMany
    private List<TeachableClass> classes;

    @OneToMany
    private List<Test> tests;
}
