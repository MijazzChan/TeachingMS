package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.objects.Studenthw;
import com.mijazz.springlearn.securities.User;
import com.mijazz.springlearn.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private StudenthwService studenthwService;

    @RequestMapping(value = "/home/index")
    public String homeindex(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("coursecount", courseService.count());
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

    @GetMapping(value = "/home/assignment")
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

    @PostMapping(value = "/home/assignment")
    @ResponseBody
    public String assignmentAjax(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") String id) throws IOException {
        if (multipartFile.isEmpty()) {
            return "非法请求";
        }
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String tempName = getRandomname(12);
        String path = "E:\\SpringWorkspace\\springlearn\\src\\main\\resources\\static\\hwfile";
        File file = new File(path, tempName + suffix);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        Studenthw temphw = new Studenthw("/hwfile/" + tempName + suffix, getUsername(), new SimpleDateFormat("yyyyMMdd").format(new Date()), Long.valueOf(id));
        studenthwService.save(temphw);
        return "上传成功";
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

    public static String getRandomname(int length) {
        String str = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 10);
            sb.append(str.charAt(random));
        }
        return sb.toString();
    }
}
