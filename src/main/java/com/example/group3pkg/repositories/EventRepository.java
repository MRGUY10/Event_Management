package com.example.group3pkg.repositories;

import com.example.group3pkg.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long>{

}
