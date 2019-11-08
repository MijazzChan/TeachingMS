package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.objects.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Iterable<Assignment> findByAsproperty(String Asproperty);
}
