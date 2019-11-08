package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.securities.User;
import com.mijazz.springlearn.service.AssignmentService;
import com.mijazz.springlearn.service.CfileService;
import com.mijazz.springlearn.service.CourseService;
import com.mijazz.springlearn.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Resource
    private CourseService courseService;

    @Resource
    private CfileService cfileService;

    @Resource
    private UserService userService;

    @Resource
    private AssignmentService assignmentService;

    @RequestMapping(value = "/home/index")
    public String homeindex(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("coursecount", courses.spliterator().getExactSizeIfKnown());
        return "/home/index";
    }

    @RequestMapping(value = "/home/coursefile")
    public String coursefile(@RequestParam("property") String property, Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("courses", courseService.getall());
        if (property == null || property.equals("all")) {
            model.addAttribute("cfiles", cfileService.getall());
        } else if (property != null) {
            model.addAttribute("cfiles", cfileService.getbyproperty(property));
        }
        return "/home/coursefile";
    }

    @RequestMapping(value = "/home/assignment")
    public String assignmentpage(@RequestParam("property") String property, Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("courses", courseService.getall());
        if (property == null || property.equals("all")) {
            model.addAttribute("assignments", assignmentService.getall());
        } else if (property != null) {
            model.addAttribute("assignments", assignmentService.getbyproperty(property));
        }
        return "/home/assignment";
    }

    @RequestMapping(value = "/home/myprofile")
    public String myprofile(Principal principal, Model model) {
        String sessionUser = getUsername();
        User sessionUserInstance = userService.findbyloginname(sessionUser);
        model.addAttribute("user", sessionUserInstance);
        return "/home/myprofile";
    }

    @GetMapping(value = "/home/editprofile")
    public String editprofile(Principal principal, Model model) {
        String sessionUser = getUsername();
        User sessionUserInstance = userService.findbyloginname(sessionUser);
        model.addAttribute("user", sessionUserInstance);
        return "/home/editprofile";
    }

    @PostMapping(value = "/home/editprofile")
    @ResponseBody
    public String editAjax(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
            return "非法请求";
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return "非法请求";
        }
        String loginname = getUsername();
        User sessionUserInstance = userService.findbyloginname(loginname);
        sessionUserInstance.setUsername(user.getUsername());
        System.out.println(user.toString());
        sessionUserInstance.setPassword(user.getPassword());
        userService.savebyloginname(sessionUserInstance);
        return "修改成功";
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
