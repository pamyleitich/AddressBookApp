package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "home"; // assuming you're using Thymeleaf templates
    }
}

