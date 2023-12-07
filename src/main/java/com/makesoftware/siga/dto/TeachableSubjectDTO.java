package com.makesoftware.siga.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class TeachableSubjectDTO {
    private Long id;

    private Long teacherId;

    private List<Integer> classTimeBlockIds;

    private List<Integer> classesIds;
}
