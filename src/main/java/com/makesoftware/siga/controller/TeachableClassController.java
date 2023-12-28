package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.SchoolSemester;
import com.makesoftware.siga.model.TeachableClass;
import com.makesoftware.siga.model.TeachableSubject;
import com.makesoftware.siga.repository.SchoolSemesterRepository;
import com.makesoftware.siga.repository.TeachableClassRepository;
import com.makesoftware.siga.repository.TeachableSubjectRepository;
import com.makesoftware.siga.util.ControllerUtils;
import com.makesoftware.siga.util.EndpointPrefixes;
import com.makesoftware.siga.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.makesoftware.siga.controller.util.TeachableClassUtil.generateClassDates;

@RestController
public class TeachableClassController {

    private final String ENDPOINT_PREFIX = EndpointPrefixes.SUBJECT + "/{subjectId}" +
            EndpointPrefixes.TEACHABLE_SUBJECT + "/{teachableSubjectId}" + EndpointPrefixes.TEACHABLE_CLASS;

    private final TeachableClassRepository teachableClassRepository;
    private final TeachableSubjectRepository teachableSubjectRepository;
    private final SchoolSemesterRepository schoolSemesterRepository;

    public TeachableClassController(TeachableClassRepository teachableClassRepository, TeachableSubjectRepository teachableSubjectRepository, SchoolSemesterRepository schoolSemesterRepository) {
        this.teachableClassRepository = teachableClassRepository;
        this.teachableSubjectRepository = teachableSubjectRepository;
        this.schoolSemesterRepository = schoolSemesterRepository;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public ResponseEntity<List<TeachableClass>> getAllClasses(@PathVariable Long subjectId, @PathVariable Long teachableSubjectId) {
        TeachableSubject teachableSubject = ControllerUtils.findById(teachableSubjectId, teachableSubjectRepository, Messages.TEACHABLE_SUBJECT_NOT_FOUND);
        return ResponseEntity.ok().body(teachableSubject.getClasses());

//        SchoolSemester schoolSemester = getSchoolSemester();
//        List<LocalDate> classDates = generateClassDates(teachableSubject, schoolSemester.getStartDate(), schoolSemester.getEndDate());
//
//        return ResponseEntity.ok().body(classDates);
    }

    @PostMapping(ENDPOINT_PREFIX)
    public ResponseEntity<TeachableClass> createClass(@PathVariable Long subjectId, @PathVariable Long teachableSubjectId, @RequestBody TeachableClass teachableClass) {
        TeachableSubject teachableSubject = ControllerUtils.findById(teachableSubjectId, teachableSubjectRepository, Messages.TEACHABLE_SUBJECT_NOT_FOUND);

        validateClass(teachableClass, teachableSubject);

        // TODO: Add the classTimeBlock and the presenceList automatically.

        teachableClassRepository.save(teachableClass);

        teachableSubject.getClasses().add(teachableClass);

        teachableSubjectRepository.save(teachableSubject);

        return ResponseEntity.ok().body(teachableClass);
    }

    private void validateClass(TeachableClass teachableClass, TeachableSubject teachableSubject) {
        // TODO: Validate that class date is within the school semester
        //  and that it's a day of week when the subject is taught
    }

    private static List<TeachableClass> getTeachableClassesByDate(LocalDate date, TeachableSubject teachableSubject) {
        List<TeachableClass> teachableClasses = teachableSubject.getClasses();

        List<TeachableClass> teachableClassesByDate = new ArrayList<>();
        for (TeachableClass teachableClass : teachableClasses) {
            if (teachableClass.getDate().equals(date)) {
                teachableClassesByDate.add(teachableClass);
            }
        }
        return teachableClassesByDate;
    }

    private SchoolSemester getSchoolSemester() {
        List<SchoolSemester> schoolSemesters = schoolSemesterRepository.findAll();
        if (schoolSemesters.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Messages.SCHOOL_SEMESTER_NEEDED.getMessage());
        }

        return schoolSemesters.getFirst();
    }
}
