package com.example.group3pkg.models;

import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Contact> collaborator;
    private String description;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String collaborators;

    private int budget;


}
