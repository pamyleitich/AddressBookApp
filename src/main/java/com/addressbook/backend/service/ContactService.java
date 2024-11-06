package com.addressbook.backend.service;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Fetch contacts with pagination and sorting
    public List<Contact> getContactsSortedAndPaginated(String sortBy, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Contact> contactsPage = contactRepository.findAll(pageable);
        return contactsPage.getContent();
    }

    // Search contacts by name (case-insensitive partial match)
    public List<Contact> searchContacts(String query) {
        return contactRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }

    // Fetch a contact by ID
    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
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

    // Export contacts to CSV format
    public void exportContactsToCSV(HttpServletResponse response) throws IOException {
        List<Contact> contacts = contactRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"contacts.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("ID,First Name,Last Name,Email,Date of Birth,Phone,Address,Birthday,Profile Picture URL");

        for (Contact contact : contacts) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    contact.getId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getDob(),
                    contact.getPhone(),
                    contact.getAddress(),
                    contact.getBirthday(),
                    contact.getProfilePictureUrl());
        }
        writer.flush();
    }

    // Import contacts from a list
    public void importContacts(List<Contact> contacts) {
        contactRepository.saveAll(contacts);
    }
}




