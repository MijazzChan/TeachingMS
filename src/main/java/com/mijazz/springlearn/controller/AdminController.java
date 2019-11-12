package com.mijazz.springlearn.controller;

import com.mijazz.springlearn.Configurator.PropertiesPathConfigurator;
import com.mijazz.springlearn.objects.Cfile;
import com.mijazz.springlearn.objects.Course;
import com.mijazz.springlearn.securities.User;
import com.mijazz.springlearn.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
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
    @Autowired
    private PropertiesPathConfigurator pathConfigurator;

    public static String getRandomname(int length) {
        String str = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 10);
            sb.append(str.charAt(random));
        }
        return sb.toString();
    }

    @RequestMapping("/admin/index")
    public String adminIndex(Model model) {
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
    public String courseManage(Model model) {
        Iterable<Course> courses = courseService.getall();
        model.addAttribute("courses", courses);
        model.addAttribute("user", getUsername());
        return "/admin/coursemanage";
    }

    @PostMapping("/admin/coursemanage")
    @ResponseBody
    public String courseAjax(@RequestBody Course course) {
        if (course == null) {
            return "非法请求";
        }
        if (course.getCourseid() != 0 && course.getCoursename().equals("-delete-")) {
            courseService.delete(course.getCourseid());
            return "删除成功";
        } else {
            courseService.save(course);
            return "保存成功";
        }

    }

    @GetMapping(value = "/admin/editprofile")
    public String editprofile(Principal principal, Model model) {
        String sessionUser = getUsername();
        User sessionUserInstance = userService.findbyloginname(sessionUser);
        model.addAttribute("user", sessionUserInstance);
        return "/admin/editprofile";
    }

    @PostMapping(value = "/admin/editprofile")
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

    @RequestMapping(value = "/admin/myprofile")
    public String myprofile(Principal principal, Model model) {
        String sessionUser = getUsername();
        User sessionUserInstance = userService.findbyloginname(sessionUser);
        model.addAttribute("user", sessionUserInstance);
        return "/admin/myprofile";
    }

    @GetMapping(value = "/admin/coursefile")
    public String coursefile(@RequestParam("property") String property, Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("courses", courseService.getall());
        if (property == null || property.equals("all")) {
            model.addAttribute("cfiles", cfileService.getall());
        } else if (property != null) {
            model.addAttribute("cfiles", cfileService.getbyproperty(property));
        }
        return "/admin/coursefile";
    }

    @PostMapping(value = "/admin/coursefile")
    @ResponseBody
    public String coursefileAjax(@RequestParam("file") MultipartFile multipartFile, @RequestParam("cfilename") String cfilename, @RequestParam("cfileproperty") String property) throws IOException {
        if (multipartFile.isEmpty()) {
            log.warn("Empty Request Pass through Spring Security /admin/coursefile");
            return "非法请求";
        }
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String tempName = getRandomname(12);
//        String path = "E:\\SpringWorkspace\\springlearn\\src\\main\\resources\\static\\cfile";
        String path = pathConfigurator.getCfilepath();
        File file = new File(path, tempName + suffix);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        Cfile cfile = new Cfile(cfilename, property, "/cfile/" + tempName + suffix);
        cfileService.save(cfile);
        log.info("Course file is uploaded via post by " + getUsername() + " in name of " + tempName);
        return "上传成功";
    }

    @RequestMapping("/admin/readassignment")
    public String ReadAssignment(Model model) {
        model.addAttribute("user", getUsername());
        return "/admin/readassignment";
    }

    @RequestMapping("/admin/usermanage")
    public String UserManage(Model model) {
        model.addAttribute("user", getUsername());
        return "/admin/usermanage";
    }

    @RequestMapping("/admin/assignment")
    public String AssignmentMan(Model model) {
        model.addAttribute("user", getUsername());
        return "/admin/assignment";
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
