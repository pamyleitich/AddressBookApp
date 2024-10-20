package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Get all contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.findAll();
    }

    // Add a new contact
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return contact;
    }
}

