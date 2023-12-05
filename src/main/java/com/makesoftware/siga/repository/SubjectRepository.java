package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.Subject;
import com.makesoftware.siga.model.TeachableSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
