package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class Event {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String eventName;
    private String venue;
    @Column(name = "event_start")
    LocalDate start;

    @Column(name = "event_end")
    LocalDate end;
    private String startTime;
    private String EndTime;
    private int Budget;
    private String contact;
    private String Description;
    private String eventType;
    String text;
    String color;



}
