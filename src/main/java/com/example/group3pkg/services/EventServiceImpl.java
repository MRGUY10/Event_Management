package com.example.group3pkg.services;

import java.util.List;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.repositories.EventRepository;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService {

	private EventRepository eventRepository;
	
	public EventServiceImpl(EventRepository eventRepository) {
		super();
		this.eventRepository = eventRepository;
	}

	@Override
	public List<Event> getAllEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Event getEventById(Long id) {
		return eventRepository.findById(id).get();
	}

	@Override
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public void deleteEventById(Long id) {
		eventRepository.deleteById(id);
	}

}
