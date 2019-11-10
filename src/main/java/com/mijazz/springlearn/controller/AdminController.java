package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    private CourseService courseService;

    @Resource
    private CfileService cfileService;

    @Resource
    private UserService userService;

    @Resource
    private AssignmentService assignmentService;

    @Resource
    private StudenthwService studenthwService;

    @RequestMapping("/admin/index")
    public String adminIndex(Model model){
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("user", getUsername());
        model.addAttribute("coursecount", courseService.count());
        model.addAttribute("usercount", userService.count());
        model.addAttribute("assignmentcount", assignmentService.count());
        model.addAttribute("assignments", assignmentService.getall());
        model.addAttribute("cfilecount", cfileService.count());
        return "/admin/index";
    }

    @GetMapping("/admin/coursemanage")
    public String courseManage(Model model){
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("user", getUsername());
        return "/admin/coursemanage";
    }

    @PostMapping("/admin/coursemanage")
    @ResponseBody
    public String courseAjax(@RequestBody Course course){
        if (course == null){
            return "非法请求";
        }
        if (course.getCourseid() != 0 || course.getCoursename().equals("-delete-")){
            courseService.delete(course.getCourseid());
        }
        return "删除成功";
    }

    private String getAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authentication.getAuthorities()) {
            roles.add(a.getAuthority());
        }

        return roles.toString();
    }

    private String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
