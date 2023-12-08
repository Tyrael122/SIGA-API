package com.makesoftware.siga.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ScheduleDTO {
    private DayOfWeek classDay;

    private LocalTime classesStartTime;
    private LocalTime classesEndTime;

    private Integer durationInMinutes;
    private Integer breakDurationInMinutes;
}
