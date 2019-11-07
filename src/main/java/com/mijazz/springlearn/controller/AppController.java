package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.service.CourseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

//    @Resource
//    private CourseService courseService;

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

//    @RequestMapping(value = "/home")
//    public String home(Model model){
//        model.addAttribute("user", getUsername());
//        model.addAttribute("role", getAuthority());
//        Iterable<Course> courses = courseService.getall();
//        model.addAttribute("courses", courses);
//        model.addAttribute("coursecount", courses.spliterator().getExactSizeIfKnown());
//        return "home";
//    }

    @RequestMapping(value = "/coursefile")
    public String coursefile(Model model){
        model.addAttribute("user", getUsername());
        return "coursefile";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "admin";
    }

    @RequestMapping(value = "accessDenied")
    public String accessDenied(Model model){
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "accessDenied";
    }

    @RequestMapping(value = "logout")
    public String logoutHandle(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

    private String getAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authentication.getAuthorities()){
            roles.add(a.getAuthority());
        }

        return roles.toString();
    }

    private String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
