package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Integer workload;

    @ManyToOne
    private Course course;

    private Integer defaultSemester;
}
