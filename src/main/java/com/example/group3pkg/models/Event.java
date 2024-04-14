package com.example.group3pkg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String venue;
    private String eventType;
    private Date eventDate;
    private String startTime;
    private String EndTime;
    private String Budget;
    private String contact;
    private String Description;


}
