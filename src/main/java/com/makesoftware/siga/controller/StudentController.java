package com.makesoftware.siga.controller;

import com.makesoftware.siga.dto.StudentDTO;
import com.makesoftware.siga.model.Role;
import com.makesoftware.siga.model.Student;
import com.makesoftware.siga.model.TeachableCourse;
import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.StudentRepository;
import com.makesoftware.siga.repository.TeachableCourseRepository;
import com.makesoftware.siga.repository.UserRepository;
import com.makesoftware.siga.util.ControllerUtils;
import com.makesoftware.siga.util.EndpointPrefixes;
import com.makesoftware.siga.util.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.makesoftware.siga.util.UserUtils.addRoleToUser;
import static com.makesoftware.siga.util.UserUtils.removeRoleFromUser;

@RestController
public class StudentController {
    private final String ENDPOINT_PREFIX = EndpointPrefixes.STUDENT;

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeachableCourseRepository teachableCourseRepository;

    private final ModelMapper modelMapper;

    public StudentController(StudentRepository studentRepository, UserRepository userRepository, TeachableCourseRepository teachableCourseRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.teachableCourseRepository = teachableCourseRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping(ENDPOINT_PREFIX + "/{id}")
    public Student getById(@PathVariable long id) {
        return ControllerUtils.findById(id, studentRepository, Messages.STUDENT_NOT_FOUND);
    }

    @PostMapping(EndpointPrefixes.USER + "/{userId}" + ENDPOINT_PREFIX)
    public Student create(@PathVariable long userId, @RequestBody StudentDTO studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);

        TeachableCourse enrolledCourse = ControllerUtils.findById(studentDto.getEnrolledCourseId(), teachableCourseRepository, Messages.TEACHABLE_COURSE_NOT_FOUND);
        student.setEnrolledCourse(enrolledCourse);

        User user = ControllerUtils.findById(userId, userRepository, Messages.USER_NOT_FOUND);
        addRoleToUser(user, Role.STUDENT);

        student.setUser(user);

        return studentRepository.save(student);
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable long id) {
        Student student = ControllerUtils.findById(id, studentRepository, Messages.STUDENT_NOT_FOUND);
        
        User user = student.getUser();
        removeRoleFromUser(user, Role.STUDENT);
        userRepository.save(user);
        
        return ControllerUtils.deleteById(id, studentRepository, Messages.STUDENT_DELETED);
    }
}
