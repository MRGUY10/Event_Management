package com.example.group3pkg.models;

import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String description;
    private LocalDate deadline;
    private String priority;
    private String status;
    private String collaborators;
    private int budget;


}
