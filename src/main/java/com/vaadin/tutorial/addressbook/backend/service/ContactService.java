package com.vaadin.tutorial.addressbook.backend.service;

import com.vaadin.tutorial.addressbook.backend.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> findAll() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
