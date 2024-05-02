package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;


@Entity
@Data
public class Event {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String eventName;
    private String venue;
    private Date StartDate;
    private Date EndDate;
    private String startTime;
    private String EndTime;
    private int Budget;
    private String contact;
    private String Description;
    private String eventType;


}
