package com.makesoftware.siga.controller;

import com.makesoftware.siga.dto.ScheduleDTO;
import com.makesoftware.siga.model.ClassTimeBlock;
import com.makesoftware.siga.repository.ClassTimeBlockRepository;
import com.makesoftware.siga.util.EndpointPrefixes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScheduleController {

    private final String ENDPOINT_PREFIX = EndpointPrefixes.SCHEDULE;

    private final ClassTimeBlockRepository classTimeBlockRepository;

    public ScheduleController(ClassTimeBlockRepository classTimeBlockRepository) {
        this.classTimeBlockRepository = classTimeBlockRepository;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public ResponseEntity<List<ClassTimeBlock>> getAll() {
        return ResponseEntity.ok(classTimeBlockRepository.findAll());
    }

    @PostMapping(ENDPOINT_PREFIX)
    public ResponseEntity<List<ClassTimeBlock>> create(@RequestBody List<ScheduleDTO> scheduleDTOs) {
        deletePreviousSchedule();

        List<ClassTimeBlock> classTimeBlocks = new ArrayList<>();

        for (ScheduleDTO schedule : scheduleDTOs) {
            validateSchedule(schedule);

            classTimeBlocks.addAll(generateClassTimeBlocks(schedule));
        }

        return ResponseEntity.ok(classTimeBlocks);
    }

    private void deletePreviousSchedule() {
        classTimeBlockRepository.deleteAll();
    }

    private void validateSchedule(ScheduleDTO schedule) {
        if (schedule.getClassesStartTime().isAfter(schedule.getClassesEndTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Classes start time cannot be after classes end time");
        }

        if (schedule.getDurationInMinutes() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duration in minutes must be greater than 0");
        }

        if (schedule.getBreakDurationInMinutes() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Break duration in minutes must be greater than or equal to 0");
        }
    }

    private List<ClassTimeBlock> generateClassTimeBlocks(ScheduleDTO schedule) {
        List<ClassTimeBlock> savedClassTimeBlocks = new ArrayList<>();

        LocalTime classesStartTime = schedule.getClassesStartTime();
        LocalTime classesEndTime = schedule.getClassesEndTime();

        LocalTime classStartTime = classesStartTime;

        while (isClassDurationWithinEndTime(schedule, classStartTime, classesEndTime)) {
            ClassTimeBlock classTimeBlock = createClassTimeBlock(schedule, classStartTime);

            classTimeBlockRepository.save(classTimeBlock);
            savedClassTimeBlocks.add(classTimeBlock);

            classStartTime = classStartTime.plusMinutes(schedule.getDurationInMinutes() + schedule.getBreakDurationInMinutes());
        }

        return savedClassTimeBlocks;
    }

    private static boolean isClassDurationWithinEndTime(ScheduleDTO schedule, LocalTime classStartTime, LocalTime classesEndTime) {
        return classStartTime.plusMinutes(schedule.getDurationInMinutes()).isBefore(classesEndTime.plusSeconds(1));
    }

    private ClassTimeBlock createClassTimeBlock(ScheduleDTO schedule, LocalTime classStartTime) {
        ClassTimeBlock classTimeBlock = new ClassTimeBlock();

        classTimeBlock.setClassDay(schedule.getClassDay());
        classTimeBlock.setClassStartTime(classStartTime);
        classTimeBlock.setClassEndTime(classStartTime.plusMinutes(schedule.getDurationInMinutes()));

        return classTimeBlock;
    }
}
