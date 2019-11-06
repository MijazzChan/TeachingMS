package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.objects.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Query(value = "update Course set coursename= :coursename, coursetime= :coursetime, courseplace= :courseplace where courseid= :courseid")
    void updatecourse(@Param("courseid") long courseid,
                      @Param("coursename") String coursename,
                      @Param("coursetime") String coursetime,
                      @Param("courseplace") String courseplace);
}
