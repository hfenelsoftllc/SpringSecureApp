package com.hfenelsoftllc.springsecureapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(HttpServletRequest  request) {
        return "User IP: \t" + request.getRemoteAddr() + "\nSession: \t"+ request.getSession().getId();
    }
}
