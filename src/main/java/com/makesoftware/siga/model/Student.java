package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student extends User {

    @ManyToOne
    private TeachableCourse enrolledCourse;

    @OneToMany
    private List<TeachableSubject> enrolledSubjects;

    private Integer courseSemester;
}
