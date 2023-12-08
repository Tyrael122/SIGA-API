package com.makesoftware.siga.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;

    private Long enrolledCourseId;

    private List<Long> enrolledSubjectIds;

    private Integer courseSemester;
}
