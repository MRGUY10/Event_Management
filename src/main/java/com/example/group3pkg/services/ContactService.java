package com.example.group3pkg.services;


import com.example.group3pkg.models.Contact;

import java.util.List;



public interface ContactService {
    List<Contact> getAllContacts();

    Contact saveContact(Contact contact);

    Contact getContactById(Long id);

    Contact updateContact(Contact contact);

    void deleteContactById(Long id);
}
