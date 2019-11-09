package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("cfilecount", cfileService.count());
        return "/admin/index";
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
