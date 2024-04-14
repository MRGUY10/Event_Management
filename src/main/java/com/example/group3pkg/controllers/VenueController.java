package com.example.group3pkg.controllers;


import com.example.group3pkg.models.Venue;
import com.example.group3pkg.services.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        super();
        this.venueService = venueService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/venue")
    public String showAllVenue(Model model) {
        List<Venue> venue = venueService.getAllVenue();
        model.addAttribute("venue", venue);
        return "Venue"; // Return the HTML template for displaying all events
    }


    @GetMapping("/venue/create")
    public String showCreateForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "create_venue";
    }

    @PostMapping("/venue/create")
    public String createVenue(@ModelAttribute Venue venue, RedirectAttributes redirectAttributes) {
        try {
            venueService.saveVenue(venue);
            redirectAttributes.addFlashAttribute("message", "Venue created successfully!");
            return "redirect:/venue/create";
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Failed to create venue: " + e.getMessage());
            return "redirect:/venue/create";
        }
    }


    @GetMapping("/venue/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
       Venue venue = venueService.getVenueById(id);
        model.addAttribute("venue", venue);
        return "edit_venue"; // Return the HTML template for editing an event
    }
    @PostMapping("/venue/update")
    public String updateVenue(@ModelAttribute Venue venue) {
        venueService.updateVenue(venue);
        return "redirect:/venue"; // Redirect to the events page after updating the event
    }

    @PostMapping("/venue/{id}")
    public String updateVenue(@PathVariable Long id,
                                @ModelAttribute("venue") Venue venue,
                                Model model) {

        // get student from database by id
        Venue existingVenue = venueService.getVenueById(id);
        existingVenue.setId(id);
        existingVenue.setName(venue.getName());
        existingVenue.setAddress(venue.getAddress());
        existingVenue.setCapacity(venue.getCapacity());
        existingVenue.setLocation(venue.getLocation());
        existingVenue.setAmenities(venue.getAmenities());

        // save updated student object
        venueService.updateVenue(existingVenue);
        return "redirect:/venue";
    }

    // handler method to handle delete student request

    @PostMapping("/venue/delete")
    public String deleteVenue(@RequestParam("id") Long eventId) {
        venueService.deleteVenueById(eventId);
        return "redirect:/venue"; // Redirect to the events page after deletion
    }

}
