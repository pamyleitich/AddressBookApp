package com.addressbook.backend.repository;

import com.addressbook.backend.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    
    // Custom query methods
    List<Contact> findByFirstName(String firstName);

    List<Contact> findByEmail(String email);

    // Search by partial match for first name or last name
    List<Contact> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    // Paging and sorting support for large lists of contacts
    Page<Contact> findAll(Pageable pageable);
}

