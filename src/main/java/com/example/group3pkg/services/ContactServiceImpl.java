package com.example.group3pkg.services;

import java.util.List;

import com.example.group3pkg.models.Contact;
import com.example.group3pkg.models.Event;
import com.example.group3pkg.repositories.ContactRepository;
import com.example.group3pkg.repositories.EventRepository;
import org.springframework.stereotype.Service;


@Service
public  class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        super();
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

}
