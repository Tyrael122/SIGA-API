package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.Course;
import com.makesoftware.siga.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
