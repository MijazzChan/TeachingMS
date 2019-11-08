package com.mijazz.springlearn.service;

import com.mijazz.springlearn.objects.Assignment;
import com.mijazz.springlearn.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AssignmentService {
    @Resource
    private AssignmentRepository assignmentRepository;

    @Transactional
    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    @Transactional
    public Iterable<Assignment> getall() {
        return assignmentRepository.findAll();
    }

    @Transactional
    public Iterable<Assignment> getbyproperty(String property) {
        return assignmentRepository.findByAsproperty(property);
    }
}
