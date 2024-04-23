package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.EventType;
import com.example.group3pkg.services.EventService;
import com.example.group3pkg.services.EventTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EventController {
	
	private EventService eventService;
	private EventTypeService eventTypeService;

	public EventController(EventService eventService,EventTypeService eventTypeService) {
		super();
		this.eventService = eventService;
		this.eventTypeService = eventTypeService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/events/user")
	public String showAllEvents(Model model) {
		List<Event> events = eventService.getAllEvent();
		List<EventType> eventTypes = eventTypeService.getAllEventType();
		model.addAttribute("events", events);
		model.addAttribute("eventTypes", eventTypes);
		return "Event"; // Return the HTML template for displaying all events
	}


	@GetMapping("/events/create")
	public String showCreateForm(Model model) {
		model.addAttribute("event", new Event());
		return "Event"; // Return the HTML template for creating an event
	}

	@PostMapping("/events/create" )
	public String createEvent(@ModelAttribute Event event) {
		eventService.saveEvent(event);
		return "Event"; // Redirect to the create event form with success message
	}

	@GetMapping("/events/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Event event = eventService.getEventById(id);
		model.addAttribute("event", event);
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
		
		// get student from database by id
		Event existingEvent = eventService.getEventById(id);
		existingEvent.setId(id);
		existingEvent.setEventName(event.getEventName());
		existingEvent.setVenue(event.getVenue());
		existingEvent.setEventType(event.getEventType());
		
		// save updated student object
		eventService.updateEvent(existingEvent);
		return "redirect:/admin";
	}
	
	// handler method to handle delete student request

	@PostMapping("/events/delete")
	public String deleteEvent(@RequestParam ("id") Long eventId) {
		eventService.deleteEventById(eventId);
		return "redirect:/events/user"; // Redirect to the events page after deletion
	}
	
}
