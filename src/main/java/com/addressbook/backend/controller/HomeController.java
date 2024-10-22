package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ContactService contactService;

    public HomeController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "index";  // Ensure this matches index.html under /templates
    }
}



