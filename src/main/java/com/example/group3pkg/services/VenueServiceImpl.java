package com.example.group3pkg.services;


import com.example.group3pkg.models.Venue;
import com.example.group3pkg.repositories.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        super();
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenue() {
        return venueRepository.findAll();
    }

    @Override
    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    @Override
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).get();
    }

    @Override
    public Venue updateVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    @Override
    public void deleteVenueById(Long id) {venueRepository.deleteById(id);
    }

}
