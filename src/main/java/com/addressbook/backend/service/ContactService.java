package com.addressbook.backend.service;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(String id, Contact contactDetails) {
        return contactRepository.findById(id)
            .map(contact -> {
                contact.setFirstName(contactDetails.getFirstName());
                contact.setLastName(contactDetails.getLastName());
                contact.setEmail(contactDetails.getEmail());
                contact.setDob(contactDetails.getDob());
                contact.setPhone(contactDetails.getPhone());
                return contactRepository.save(contact);
            })
            .orElseThrow(() -> new RuntimeException("Contact not found with id " + id));
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}




