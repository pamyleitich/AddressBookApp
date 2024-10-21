package com.addressbook.backend.controller;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable String id, @RequestBody Contact contactDetails) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFirstName(contactDetails.getFirstName());
            contact.setLastName(contactDetails.getLastName());
            contact.setEmail(contactDetails.getEmail());
            contact.setDob(contactDetails.getDob());
            contact.setPhone(contactDetails.getPhone());
            return contactRepository.save(contact); 
        } else {
            throw new RuntimeException("Contact not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable String id) {
        contactRepository.deleteById(id);
    }
}




