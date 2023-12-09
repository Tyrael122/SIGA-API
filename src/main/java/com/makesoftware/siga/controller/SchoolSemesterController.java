package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.SchoolSemester;
import com.makesoftware.siga.repository.SchoolSemesterRepository;
import com.makesoftware.siga.util.EndpointPrefixes;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolSemesterController {

    private final String ENDPOINT_PREFIX = EndpointPrefixes.SEMESTER;
    private final SchoolSemesterRepository schoolSemesterRepository;


    public SchoolSemesterController(SchoolSemesterRepository schoolSemesterRepository) {
        this.schoolSemesterRepository = schoolSemesterRepository;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public ResponseEntity<SchoolSemester> getSchoolSemester() {
        List<SchoolSemester> schoolSemesters = schoolSemesterRepository.findAll();
        if (schoolSemesters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(schoolSemesters.getFirst());
    }

    @PostMapping(ENDPOINT_PREFIX)
    public SchoolSemester createSchoolSemester(@RequestBody @Valid SchoolSemester schoolSemester) {
        schoolSemesterRepository.deleteAll();

        return schoolSemesterRepository.save(schoolSemester);
    }
}
