package com.makesoftware.siga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class ClassTimeBlock {

    @Id
    @GeneratedValue
    private Long id;

    private DayOfWeek classDay;

    private LocalTime classStartTime;
    private LocalTime classEndTime;
}
