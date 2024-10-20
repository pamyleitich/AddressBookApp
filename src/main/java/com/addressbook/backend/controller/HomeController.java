package com.addressbook.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";  // This should map to src/main/resources/templates/index.html
    }