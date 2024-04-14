package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Venue;
import com.example.group3pkg.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueRAPIController {

    private final VenueService venueService;

    @Autowired
    public VenueRAPIController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venues = venueService.getAllVenue();
        return ResponseEntity.ok().body(venues);
    }

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        Venue createdVenue = venueService.saveVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVenue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
        Venue venue = venueService.getVenueById(id);
        if (venue != null) {
            return ResponseEntity.ok().body(venue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long id, @RequestBody Venue venueDetails) {
        Venue existingVenue = venueService.getVenueById(id);
        if (existingVenue != null) {
            existingVenue.setName(venueDetails.getName());
            existingVenue.setAddress(venueDetails.getAddress());
            existingVenue.setCapacity(venueDetails.getCapacity());
            existingVenue.setLocation(venueDetails.getLocation());
            existingVenue.setAmenities(venueDetails.getAmenities());
            venueService.updateVenue(existingVenue);
            return ResponseEntity.ok().body(existingVenue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        Venue existingVenue = venueService.getVenueById(id);
        if (existingVenue != null) {
            venueService.deleteVenueById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
