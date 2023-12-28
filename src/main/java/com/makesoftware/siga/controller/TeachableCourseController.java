package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.Course;
import com.makesoftware.siga.model.TeachableCourse;
import com.makesoftware.siga.repository.CourseRepository;
import com.makesoftware.siga.repository.TeachableCourseRepository;
import com.makesoftware.siga.util.ControllerUtils;
import com.makesoftware.siga.util.EndpointPrefixes;
import com.makesoftware.siga.util.Messages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeachableCourseController {

    private static final String ENDPOINT_PREFIX = EndpointPrefixes.TEACHABLE_COURSE;
    private final TeachableCourseRepository teachableCourseRepository;
    private final CourseRepository courseRepository;

    public TeachableCourseController(TeachableCourseRepository teachableCourseRepository, CourseRepository courseRepository) {
        this.teachableCourseRepository = teachableCourseRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping(EndpointPrefixes.COURSE + "/{courseId}" + ENDPOINT_PREFIX)
    public List<TeachableCourse> getAllTeachableCourses(@PathVariable Long courseId) {
        return teachableCourseRepository.findAllByBasisCourse_Id(courseId);
    }

    @GetMapping(EndpointPrefixes.COURSE + "/{courseId}" + ENDPOINT_PREFIX + "/{id}")
    public TeachableCourse getTeachableCourse(@PathVariable Long courseId, @PathVariable Long id) {
        return ControllerUtils.findById(id, teachableCourseRepository, Messages.TEACHABLE_COURSE_NOT_FOUND);
    }

    @PostMapping(EndpointPrefixes.COURSE + "/{courseId}" + ENDPOINT_PREFIX)
    public TeachableCourse createTeachableCourse(@PathVariable Long courseId, @RequestBody TeachableCourse teachableCourse) {
        Course course = ControllerUtils.findById(courseId, courseRepository, Messages.COURSE_NOT_FOUND);
        teachableCourse.setBasisCourse(course);

        return teachableCourseRepository.save(teachableCourse);
    }

    @DeleteMapping(EndpointPrefixes.COURSE + "/{courseId}" + ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> deleteTeachableCourse(@PathVariable Long courseId, @PathVariable Long id) {
        return ControllerUtils.deleteById(id, teachableCourseRepository, Messages.TEACHABLE_COURSE_DELETED);
    }
}
