package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Contact;
import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.EventType;
import com.example.group3pkg.models.Venue;
import com.example.group3pkg.services.ContactService;
import com.example.group3pkg.services.EventService;
import com.example.group3pkg.services.EventTypeService;
import com.example.group3pkg.services.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EventController {
	
	private EventService eventService;
	private EventTypeService eventTypeService;
	private  VenueService venueService;
	private ContactService contactService;

	public EventController(EventService eventService,EventTypeService eventTypeService,VenueService venueService,ContactService contactService) {
		super();
		this.eventService = eventService;
		this.eventTypeService = eventTypeService;
		this.venueService = venueService;
		this.contactService = contactService;

	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/events/user")
	public String showAllEvents(Model model) {
		List<Event> events = eventService.getAllEvent();
		List<EventType> eventTypes = eventTypeService.getAllEventType();
		model.addAttribute("events", events);
		model.addAttribute("eventTypes", eventTypes);
		List<Venue> venue = venueService.getAllVenue();
		model.addAttribute("venue", venue);
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		return "Event"; // Return the HTML template for displaying all events
	}


	@GetMapping("/calendar")
	public String displayCalendar() {
		return "index"; // Return the HTML template for creating an event
	}


	@PostMapping("/events/create" )
	public String createEvent(@ModelAttribute Event event) {
		eventService.saveEvent(event);
		return "redirect:/events/user"; // Redirect to the create event form with success message
	}

	@GetMapping("/events/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Event event = eventService.getEventById(id);
		List<EventType> eventTypes = eventTypeService.getAllEventType();
		List<Venue> venue = venueService.getAllVenue();
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("event", event);
		model.addAttribute("contact", contacts);
		model.addAttribute("venue", venue);
		model.addAttribute("eventTypes", eventTypes);
		return "edit_Event"; // Return the HTML template for editing an event
	}
	@PostMapping("/events/update")
	public String updateEvent(@ModelAttribute Event event) {

		eventService.updateEvent(event);
		return "redirect:/events/user"; // Redirect to the events page after updating the event
	}

	@PostMapping("/admin/{id}")
	public String updateEvent(@PathVariable Long id,
			@ModelAttribute("event") Event event,
			Model model) {

		Event existingEvent = eventService.getEventById(id);
		existingEvent.setId(id);
		existingEvent.setText(event.getText());
		existingEvent.setVenue(event.getVenue());
		existingEvent.setEventType(event.getEventType());
		

		eventService.updateEvent(existingEvent);
		return "redirect:/admin";
	}


	@PostMapping("/events/delete")
	public String deleteEvent(@RequestParam ("id") Long eventId) {
		eventService.deleteEventById(eventId);
		return "redirect:/events/user"; // Redirect to the events page after deletion
	}
	
}
