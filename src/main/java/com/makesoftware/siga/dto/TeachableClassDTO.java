package com.makesoftware.siga.dto;

import com.makesoftware.siga.model.TeachableClass;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TeachableClassDTO {
    private LocalDate date;
    private List<TeachableClass> teachableClasses;
}
