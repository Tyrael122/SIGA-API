package com.makesoftware.siga.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;

    @NotNull
    private Long enrolledCourseId;

    private List<Long> enrolledSubjectIds;

    private Integer courseSemester;
}
