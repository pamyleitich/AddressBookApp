package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    // Constructor-based dependency injection
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Fetch all contacts with optional sorting and pagination
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(
            @RequestParam(required = false, defaultValue = "firstName") String sortBy,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        List<Contact> contacts = contactService.getContactsSortedAndPaginated(sortBy, page, size);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // Fetch a contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        return contactService.getContactById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add a new contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact newContact = contactService.addContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    // Update an existing contact
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact contactDetails) {
        try {
            Contact updatedContact = contactService.updateContact(id, contactDetails);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        try {
            contactService.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Export contacts to CSV
    @GetMapping("/export")
    public void exportContactsToCSV(HttpServletResponse response) throws IOException {
        contactService.exportContactsToCSV(response);
    }

    // Import contacts from a list
    @PostMapping("/import")
    public ResponseEntity<HttpStatus> importContacts(@RequestBody List<Contact> contacts) {
        contactService.importContacts(contacts);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}





