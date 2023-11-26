package com.makesoftware.siga.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StudentGrade {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Student student;
    private Integer grade;
}
