package com.mijazz.springlearn.service;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.repository.CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Resource
    private CourseRepository courseRepository;

    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void saveall(List<Course> courses) {
        courseRepository.saveAll(courses);
    }

    @Transactional
    public void delete(long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public Iterable<Course> getall() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course getbyid(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.get();
    }

    @Transactional
    public void update(Course course) {
        courseRepository.updatecourse(course.getCourseid(), course.getCoursename(), course.getCoursetime(), course.getCourseplace());
    }

    @Transactional
    public long count(){
        return courseRepository.count();
    }
}
