package com.example.group3pkg.services;

import com.example.group3pkg.models.EventType;

import java.util.List;

public interface EventTypeService {
    List<EventType> getAllEventType();

    EventType saveEventType(EventType eventType);

    EventType getEventTypeById(Long id);

    EventType updateEventType(EventType eventType);

    void deleteEventTypeById(Long id);
}

