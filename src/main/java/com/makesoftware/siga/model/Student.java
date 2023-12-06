package com.makesoftware.siga.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @ManyToOne
    private TeachableCourse enrolledCourse;

    @OneToMany
    private List<TeachableSubject> enrolledSubjects;

    private Integer courseSemester;

}
