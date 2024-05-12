package com.example.group3pkg.services;

import com.example.group3pkg.models.EventType;
import com.example.group3pkg.repositories.EventTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventTypeServiceImpl implements EventTypeService {

    private EventTypeRepository eventTypeRepository;

    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository) {
        super();
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public List<EventType> getAllEventType() {
        return eventTypeRepository.findAll();
    }

    @Override
    public EventType saveEventType(EventType eventType) { return eventTypeRepository.save(eventType);
    }

    @Override
    public EventType getEventTypeById(Long id) {
        return eventTypeRepository.findById(id).get();
    }

    @Override
    public EventType updateEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    @Override
    public void deleteEventTypeById(Long id) {
        eventTypeRepository.deleteById(id);
    }

}