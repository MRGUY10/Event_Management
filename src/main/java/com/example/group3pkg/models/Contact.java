package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String name;
    @Column (unique = true)
    private int phone;
    @Column (unique = true)
    private String email;



}
