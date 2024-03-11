package com.example.springboot.cruddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/api")
    public String index() {
        return "/main/resources/static/index.html";
    }
}
