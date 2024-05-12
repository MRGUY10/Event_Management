package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.EventType;
import com.example.group3pkg.services.EventTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EventTypeController {
    private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        super();
        this.eventTypeService = eventTypeService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/EventType/user")
    public String showAllEventTypes(Model model) {
        List<EventType> eventTypes = eventTypeService.getAllEventType();
        model.addAttribute("eventTypes", eventTypes);
        return "Event"; // Return the HTML template for displaying all events
    }


    @GetMapping("/EventType/create")
    public String showCreateForm(Model model) {
        model.addAttribute("eventType", new EventType());
        return "Event"; // Return the HTML template for creating an event
    }

    @PostMapping("/EventType/create")
    public String createEventType(@ModelAttribute EventType eventType, RedirectAttributes redirectAttributes) {
        try {
            eventTypeService.saveEventType(eventType);
            redirectAttributes.addFlashAttribute("message", "EventType created successfully!");
            return "redirect:/events/user";
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Failed to create EventType: " + e.getMessage());
            return "redirect:/events/user";
        }
    }

    @GetMapping("/EventType/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EventType eventType = eventTypeService.getEventTypeById(id);
        model.addAttribute("eventType", eventType);
        return "edit_EventType"; // Return the HTML template for editing an event
    }
    @PostMapping("/EventType/update")
    public String updateEvent(@ModelAttribute EventType eventType) {
        eventTypeService.updateEventType(eventType);
        return "redirect:/events/user"; // Redirect to the events page after updating the event
    }

    @PostMapping("/admin2/{id}")
    public String updateEventType(@PathVariable Long id,
                              @ModelAttribute("eventType") EventType eventType,
                              Model model) {

        // get student from database by id
        EventType existingEventType = eventTypeService.getEventTypeById(id);
        existingEventType.setId(id);
        existingEventType.setName(eventType.getName());

        // save updated student object
        eventTypeService.updateEventType(existingEventType);
        return "redirect:/admin";
    }

    // handler method to handle delete student request

    @PostMapping("/EventType/delete")
    public String deleteEvent(@RequestParam("id") Long eventId) {
        eventTypeService.deleteEventTypeById(eventId);
        return "redirect:/events/user"; // Redirect to the events page after deletion
    }

}

