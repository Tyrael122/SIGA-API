package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
