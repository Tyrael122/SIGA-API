package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.Subject;
import com.makesoftware.siga.model.TeachableSubject;
import com.makesoftware.siga.repository.SubjectRepository;
import com.makesoftware.siga.util.EndpointPrefixes;
import com.makesoftware.siga.util.Messages;
import com.makesoftware.siga.util.ControllerUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SubjectController {

    private final String ENDPOINT_PREFIX = EndpointPrefixes.SUBJECT;

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @PostMapping(ENDPOINT_PREFIX)
    public Subject create(@Valid @RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @GetMapping(ENDPOINT_PREFIX + "/{id}")
    public Subject getById(@PathVariable long id) {
        return ControllerUtils.findById(id, subjectRepository, Messages.SUBJECT_NOT_FOUND);
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        return ControllerUtils.deleteById(id, subjectRepository, Messages.SUBJECT_DELETED);
    }
}
