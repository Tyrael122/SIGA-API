package com.makesoftware.siga.controller;

import com.makesoftware.siga.dto.TeacherDTO;
import com.makesoftware.siga.model.GraduationLevel;
import com.makesoftware.siga.model.Role;
import com.makesoftware.siga.model.Teacher;
import com.makesoftware.siga.model.User;
import com.makesoftware.siga.repository.TeacherRepository;
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

// TODO: See the possibility of merging this controller with the StudentController
@RestController
public class TeacherController {
    private final String ENDPOINT_PREFIX = EndpointPrefixes.TEACHER;

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public TeacherController(TeacherRepository teacherRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();

        return convertToDto(teachers);
    }

    @GetMapping(ENDPOINT_PREFIX + "/{id}")
    public Teacher getById(@PathVariable long id) {
        return ControllerUtils.findById(id, teacherRepository, Messages.TEACHER_NOT_FOUND);
    }

    @PostMapping(EndpointPrefixes.USER + "/{userId}" + ENDPOINT_PREFIX)
    public Teacher create(@PathVariable long userId, @RequestBody TeacherDTO teacherDto) {
        Teacher teacher = parseDto(teacherDto);

        User user = ControllerUtils.findById(userId, userRepository, Messages.USER_NOT_FOUND);
        addRoleToUser(user, Role.TEACHER);

        teacher.setUser(user);

        return teacherRepository.save(teacher);
    }

    private Teacher parseDto(TeacherDTO teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacher.setGraduationLevel(GraduationLevel.fromFriendlyName(teacherDto.getGraduationLevel()));
        return teacher;
    }

    private List<TeacherDTO> convertToDto(List<Teacher> teachers) {
        List<TeacherDTO> teacherDtos = teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).toList();

        teacherDtos.forEach(TeacherDTO::selfParseGraduationLevel);

        return teacherDtos;
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable long id) {
        Teacher teacher = ControllerUtils.findById(id, teacherRepository, Messages.TEACHER_NOT_FOUND);

        User user = teacher.getUser();
        removeRoleFromUser(teacher.getUser(), Role.TEACHER);
        userRepository.save(user);

        return ControllerUtils.deleteById(id, teacherRepository, Messages.TEACHER_DELETED);
    }
}
