package com.makesoftware.siga.controller.util;

import com.makesoftware.siga.model.ClassTimeBlock;
import com.makesoftware.siga.model.TeachableSubject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeachableClassUtil {

    public static List<LocalDate> generateClassDates(TeachableSubject teachableSubject, LocalDate startDate, LocalDate endDate) {
        Set<DayOfWeek> classDaysOfWeek = parseClassesDaysOfWeek(teachableSubject);

        List<LocalDate> classDates = new ArrayList<>();
        for (DayOfWeek dayOfWeek : classDaysOfWeek) {
            TemporalAdjuster nextWeekDay = TemporalAdjusters.next(dayOfWeek);

            LocalDate nextClassDate = startDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));

            while (isNextClassDateBeforeEndDate(endDate, nextClassDate)) {
                classDates.add(nextClassDate);

                nextClassDate = nextClassDate.with(nextWeekDay);
            }
        }

        return classDates;
    }

    private static boolean isNextClassDateBeforeEndDate(LocalDate endDate, LocalDate nextClassDate) {
        return nextClassDate.isBefore(endDate.plusDays(1));
    }

    private static Set<DayOfWeek> parseClassesDaysOfWeek(TeachableSubject teachableSubject) {
        return teachableSubject.getSchedule().stream().map(ClassTimeBlock::getClassDay).collect(Collectors.toSet());
    }
}
