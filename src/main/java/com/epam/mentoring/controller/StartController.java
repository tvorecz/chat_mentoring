package com.epam.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class StartController {


    @GetMapping
    @ResponseBody
    public String getMainPage() {
        return "hello";
    }

}
