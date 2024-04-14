package com.example.group3pkg.services;

import com.example.group3pkg.models.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue();

    Venue saveVenue(Venue venue);

    Venue getVenueById(Long id);

    Venue updateVenue(Venue venue);

    void deleteVenueById(Long id);
}