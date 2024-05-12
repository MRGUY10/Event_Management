package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column (unique = true)
    private String address;
    private int capacity;
    private String location;
    private int Latitudes;
    private int Longitudes;



}