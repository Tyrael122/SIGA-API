package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Teacher extends User {

    @OneToMany
    private List<TeachableSubject> teachingSubjects;
}
