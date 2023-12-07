package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.Subject;
import com.makesoftware.siga.model.TeachableSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachableSubjectRepository extends JpaRepository<TeachableSubject, Long> {
    List<TeachableSubject> findAllByBasisSubject_Id(Long subjectId);
    List<TeachableSubject> findAllByTeacher_Id(Long teacherId);
}
