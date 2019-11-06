package com.mijazz.springlearn.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrController implements ErrorController {
    @RequestMapping("/error")
    public String handleErr(HttpServletRequest request, Model model){
        Integer httpStatus = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("code", httpStatus);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
