package com.makesoftware.siga.repository;

import com.makesoftware.siga.model.TeachableClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TeachableClassRepository extends JpaRepository<TeachableClass, Long> {
    List<TeachableClass> findAllByDate(LocalDate date);
}
