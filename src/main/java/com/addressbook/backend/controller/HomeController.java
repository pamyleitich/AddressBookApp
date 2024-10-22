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

    // Constructor-based dependency injection for ContactService
    public HomeController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Fetch contacts from MongoDB using the ContactService
        List<Contact> contacts = contactService.getAllContacts();  // Call getAllContacts instead of findAll
        model.addAttribute("contacts", contacts);  // Adding the contacts list to the model

        return "index"; // Make sure you have an index.html under /templates
    }
}

