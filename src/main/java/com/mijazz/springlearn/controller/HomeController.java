package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Cfile;
import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.service.CfileService;
import com.mijazz.springlearn.service.CourseService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Resource
    private CourseService courseService;

    @Resource
    private CfileService cfileService;

    @RequestMapping(value = "/home/index")
    public String homeindex(Model model){
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("coursecount", courses.spliterator().getExactSizeIfKnown());
        return "/home/index";
    }

    @RequestMapping(value = "/home/coursefile")
    public String coursefile(@RequestParam("property")String property,  Model model){
        model.addAttribute("user", getUsername());
        model.addAttribute("courses", courseService.getall());
        if (property == null || property.equals("all")){
            model.addAttribute("cfiles", cfileService.getall());
        }else if (property != null){
            model.addAttribute("cfiles", cfileService.getbyproperty(property));
        }
        return "/home/coursefile";
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
