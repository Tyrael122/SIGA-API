package com.makesoftware.siga.controller.util;

import com.makesoftware.siga.model.ClassTimeBlock;
import com.makesoftware.siga.model.TeachableSubject;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeachableClassUtilTest {

    @Test
    void generateClassDates() {
        List<DayOfWeek> daysOfWeek = List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
        TeachableSubject teachableSubject = createTeachableSubjectWithSchedule(daysOfWeek);

        LocalDate startDate = LocalDate.of(2023, 8, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        List<LocalDate> classDates = TeachableClassUtil.generateClassDates(teachableSubject, startDate, endDate);

        assertEquals(65, classDates.size());
    }

    private static TeachableSubject createTeachableSubjectWithSchedule(List<DayOfWeek> daysOfWeek) {
        List<ClassTimeBlock> schedule = daysOfWeek.stream().map(TeachableClassUtilTest::createClassTimeBlock).toList();

        TeachableSubject teachableSubject = new TeachableSubject();
        teachableSubject.setSchedule(schedule);

        return teachableSubject;
    }

    private static ClassTimeBlock createClassTimeBlock(DayOfWeek dayOfWeek) {
        ClassTimeBlock classTimeBlock = new ClassTimeBlock();
        classTimeBlock.setClassDay(dayOfWeek);

        return classTimeBlock;
    }
}