package com.addressbook.backend.service;

import com.addressbook.backend.model.Contact;
import com.addressbook.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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

    // Search contacts by name with pagination
    public List<Contact> searchContacts(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, pageable).getContent();
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
                    contact.setBirthday(contactDetails.getBirthday());
                    contact.setPhone(contactDetails.getPhone());
                    contact.setAddress(contactDetails.getAddress());
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
        writer.println("ID,First Name,Last Name,Email,Birthday,Phone,Address,Profile Picture URL");

        for (Contact contact : contacts) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    contact.getId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getBirthday(),
                    contact.getPhone(),
                    contact.getAddress(),
                    contact.getProfilePictureUrl());
        }
        writer.flush();
    }

    // Import contacts from a CSV file
    public void importContacts(MultipartFile file) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7) { // Ensure minimum columns are present
                    Contact contact = new Contact();
                    contact.setFirstName(data[0]);
                    contact.setLastName(data[1]);
                    contact.setEmail(data[2]);
                    contact.setBirthday(LocalDate.parse(data[3])); // Adjust if date format differs
                    contact.setPhone(data[4]);
                    contact.setAddress(data[5]);
                    contact.setProfilePictureUrl(data[6]);
                    contacts.add(contact);
                }
            }
        }
        contactRepository.saveAll(contacts);
    }
}







