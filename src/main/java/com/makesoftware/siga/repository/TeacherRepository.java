package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
