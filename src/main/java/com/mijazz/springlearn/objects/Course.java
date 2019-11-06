package com.mijazz.springlearn.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_course")
public class Course {
    @Id
    private long courseid;

    @Column(name = "coursename")
    private String coursename;

    @Column(name = "coursetime")
    private String coursetime;

    @Column(name = "courseplace")
    private String courseplace;

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursetime() {
        return coursetime;
    }

    public void setCoursetime(String coursetime) {
        this.coursetime = coursetime;
    }

    public String getCourseplace() {
        return courseplace;
    }

    public void setCourseplace(String courseplace) {
        this.courseplace = courseplace;
    }
}
