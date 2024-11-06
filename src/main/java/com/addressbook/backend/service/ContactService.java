package com.addressbook.backend.service;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    // Constructor-based dependency injection (recommended)
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Fetch all contacts with pagination and sorting
    public List<Contact> getContactsSortedAndPaginated(String sortBy, int page, int size) {
        Page<Contact> contactPage = contactRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        return contactPage.getContent();
    }

    // Search contacts by first or last name (partial, case-insensitive match)
    public List<Contact> searchContacts(String query) {
        return contactRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }

    // Fetch all contacts without pagination
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Add a new contact
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Update an existing contact
    public Contact updateContact(String id, Contact contactDetails) {
        return contactRepository.findById(id)
            .map(contact -> {
                contact.setFirstName(contactDetails.getFirstName());
                contact.setLastName(contactDetails.getLastName());
                contact.setEmail(contactDetails.getEmail());
                contact.setDob(contactDetails.getDob());
                contact.setPhone(contactDetails.getPhone());
                contact.setAddress(contactDetails.getAddress());
                contact.setBirthday(contactDetails.getBirthday());
                contact.setProfilePictureUrl(contactDetails.getProfilePictureUrl());
                return contactRepository.save(contact);
            })
            .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    // Delete a contact by ID
    public void deleteContact(String id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contact not found with id: " + id);
        }
    }
}

