package com.example.group3pkg.models;

import jakarta.annotation.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate deadline;
    private String priority;
    private String assignee;
    private String status;
    private String collaborators;
    private int budget;


}
