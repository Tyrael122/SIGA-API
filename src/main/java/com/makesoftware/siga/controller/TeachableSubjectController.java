package com.makesoftware.siga.controller;

import com.makesoftware.siga.dto.TeachableSubjectDTO;
import com.makesoftware.siga.model.Subject;
import com.makesoftware.siga.model.TeachableSubject;
import com.makesoftware.siga.model.Teacher;
import com.makesoftware.siga.repository.SubjectRepository;
import com.makesoftware.siga.repository.TeachableSubjectRepository;
import com.makesoftware.siga.repository.TeacherRepository;
import com.makesoftware.siga.util.ControllerUtils;
import com.makesoftware.siga.util.EndpointPrefixes;
import com.makesoftware.siga.util.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeachableSubjectController {

    private final SubjectRepository subjectRepository;
    private final TeachableSubjectRepository teachableSubjectRepository;
    private final TeacherRepository teacherRepository;

    private final ModelMapper modelMapper;

    public TeachableSubjectController(SubjectRepository subjectRepository, TeachableSubjectRepository teachableSubjectRepository, ModelMapper modelMapper, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teachableSubjectRepository = teachableSubjectRepository;
        this.modelMapper = modelMapper;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping(EndpointPrefixes.SUBJECT + "/{subjectId}/teachable-subjects")
    public TeachableSubject createTaughtSubject(@PathVariable Long subjectId, @RequestBody TeachableSubjectDTO teachableSubjectDTO) {
        TeachableSubject teachableSubject = parseDto(teachableSubjectDTO);

        Subject subject = ControllerUtils.findById(subjectId, subjectRepository, Messages.SUBJECT_NOT_FOUND);
        teachableSubject.setBasisSubject(subject);

        return teachableSubjectRepository.save(teachableSubject);
    }

    @GetMapping(EndpointPrefixes.SUBJECT + "/{subjectId}/teachable-subjects")
    public List<TeachableSubject> getAllTaughtSubjects(@PathVariable Long subjectId) {
        return teachableSubjectRepository.findAllByBasisSubject_Id(subjectId);
    }

    @DeleteMapping(EndpointPrefixes.SUBJECT + "/{subjectId}/teachable-subjects/{id}")
    public ResponseEntity<Map<String, String>> deleteTaughtSubject(@PathVariable Long subjectId, @PathVariable Long id) {
        return ControllerUtils.deleteById(id, teachableSubjectRepository, Messages.TEACHABLE_SUBJECT_DELETED);
    }

    private TeachableSubject parseDto(TeachableSubjectDTO teachableSubjectDTO) {
        TeachableSubject teachableSubject = modelMapper.map(teachableSubjectDTO, TeachableSubject.class);

        Teacher teacher = ControllerUtils.findById(teachableSubjectDTO.getTeacherId(), teacherRepository, Messages.TEACHER_NOT_FOUND);
        teachableSubject.setTeacher(teacher);

        return teachableSubject;
    }
}
