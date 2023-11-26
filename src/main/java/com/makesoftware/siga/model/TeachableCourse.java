package com.makesoftware.siga.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class TeachableCourse {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Course basisCourse;

    private Shift shift;
}
