package com.makesoftware.siga.controller;

import com.makesoftware.siga.model.Course;
import com.makesoftware.siga.model.Subject;
import com.makesoftware.siga.repository.CourseRepository;
import com.makesoftware.siga.repository.SubjectRepository;
import com.makesoftware.siga.util.ControllerUtils;
import com.makesoftware.siga.util.Messages;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@RestController
public class CourseController {
    private final String ENDPOINT_PREFIX = "/courses";
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public CourseController(CourseRepository courseRepository, SubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping(ENDPOINT_PREFIX)
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @PostMapping(ENDPOINT_PREFIX)
    public Course create(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }

    @GetMapping(ENDPOINT_PREFIX + "/{id}")
    public Course getById(@PathVariable long id) {
        return ControllerUtils.findById(id, courseRepository, Messages.COURSE_NOT_FOUND);
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        return ControllerUtils.deleteById(id, courseRepository, Messages.COURSE_DELETED);
    }

    // TODO: Improve this method so as to accept a list of subjects, not just one.
    @PostMapping(ENDPOINT_PREFIX + "/{courseId}/subjects")
    public Course addSubject(@PathVariable Long courseId, @RequestParam long subjectId) {
        return updateCourseSubjects(courseId, subjectId,
                (loadedCourse, loadedSubject) -> loadedCourse.getSubjects().add(loadedSubject));
    }

    @DeleteMapping(ENDPOINT_PREFIX + "/{courseId}/subjects")
    public Course deleteSubject(@PathVariable Long courseId, @RequestParam long subjectId) {
        return updateCourseSubjects(courseId, subjectId,
                (loadedCourse, loadedSubject) -> loadedCourse.getSubjects().remove(loadedSubject));
    }

    public Course updateCourseSubjects(long courseId, long subjectId, BiConsumer<Course, Subject> updateFunction) {
        Course course = ControllerUtils.findById(courseId, courseRepository, Messages.COURSE_NOT_FOUND);
        Subject subject = ControllerUtils.findById(subjectId, subjectRepository, Messages.SUBJECT_NOT_FOUND);

        updateFunction.accept(course, subject);

        return courseRepository.save(course);
    }
}
