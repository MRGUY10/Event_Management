package com.example.group3pkg.repositories;

import com.example.group3pkg.models.EventType;
import com.example.group3pkg.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}