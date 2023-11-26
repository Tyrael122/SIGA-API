package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime scheduledTime;

    @OneToMany
    private List<StudentGrade> studentGrades;
}
