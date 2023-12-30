package com.makesoftware.siga.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    private Long id;

    private String acronym;

    private String name;
    private String description;

    private Integer numberOfSemesters;
    private Integer maxNumbersOfSemestersToFinish;

    private List<Long> subjectIds;
}
