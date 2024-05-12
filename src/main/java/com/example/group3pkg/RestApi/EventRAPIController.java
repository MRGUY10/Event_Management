package com.example.group3pkg.RestApi;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventRAPIController {

    private final EventService eventService;

    public EventRAPIController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/event/list")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvent();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/api/event/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.saveEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/api/event/update")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(event);
        if (updatedEvent != null) {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
