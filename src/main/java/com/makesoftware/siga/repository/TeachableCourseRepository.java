package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.TeachableCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachableCourseRepository extends JpaRepository<TeachableCourse, Long> {
    List<TeachableCourse> findAllByBasisCourse_Id(Long courseId);
}
