package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final ContactService contactService;

    public HomeController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sortBy", required = false, defaultValue = "firstName") String sortBy,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model) {

        List<Contact> contacts;

        if (search != null && !search.isEmpty()) {
            // Pass all required parameters to searchContacts
            contacts = contactService.searchContacts(search, page, size);
        } else {
            contacts = contactService.getContactsSortedAndPaginated(sortBy, page, size);
        }

        model.addAttribute("contacts", contacts);
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "index";  // Ensure this matches index.html under /templates
    }
}





