package com.example.group3pkg.repositories;

import com.example.group3pkg.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
