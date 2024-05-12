package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class EventType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String Name;
}
