package com.example.group3pkg.services;

import com.example.group3pkg.models.Event;

import java.util.List;



public interface EventService {
	List<Event> getAllEvent();

	Event saveEvent(Event event);

	Event getEventById(Long id);

	Event updateEvent(Event student);

	void deleteEventById(Long id);
}
