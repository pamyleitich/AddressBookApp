package com.addressbook.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.addressbook.backend.service.ContactService;

@Controller
public class HomeController {

    private final ContactService contactService;

    public HomeController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Add necessary model attributes for Thymeleaf rendering
        model.addAttribute("contacts", contactService.getAllContacts());
        return "index";  // Make sure this maps to /templates/index.html
    }
}


