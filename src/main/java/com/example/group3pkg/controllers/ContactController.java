package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Contact;
import com.example.group3pkg.models.Event;
import com.example.group3pkg.services.ContactService;
import com.example.group3pkg.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        super();
        this.contactService = contactService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/contacts")
    public String showAllContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "Contact"; // Return the HTML template for displaying all events
    }


    @GetMapping("/contacts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "create_contact";
    }

    @PostMapping("/contacts/create")
    public String createContact(@ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
        try {
            contactService.saveContact(contact);
            redirectAttributes.addFlashAttribute("message", "Contact created successfully!");
            return "redirect:/contacts/create";
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Failed to create contact: " + e.getMessage());
            return "redirect:/contacts/create";
        }
    }


    @GetMapping("/contacts/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "edit_contact"; // Return the HTML template for editing an event
    }
    @PostMapping("/contacts/update")
    public String updateContact(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/contacts"; // Redirect to the events page after updating the event
    }

    @PostMapping("/Contacts/{id}")
    public String updateContact(@PathVariable Long id,
                              @ModelAttribute("contact") Contact contact,
                              Model model) {

        // get student from database by id
        Contact existingContact = contactService.getContactById(id);
        existingContact.setId(id);
        existingContact.setName(contact.getName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setPhone(contact.getPhone());

        // save updated student object
        contactService.updateContact(existingContact);
        return "redirect:/contact";
    }

    // handler method to handle delete student request

    @PostMapping("/contacts/delete")
    public String deleteEvent(@RequestParam ("id") Long eventId) {
        contactService.deleteContactById(eventId);
        return "redirect:/contacts"; // Redirect to the events page after deletion
    }

}
