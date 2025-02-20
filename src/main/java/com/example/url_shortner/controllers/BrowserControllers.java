package com.example.url_shortner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrowserControllers {
    @GetMapping(value = "/")
    public String index() {
        return "home";
    }
}
