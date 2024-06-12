package com.example.group3pkg.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(name = "event_start", nullable = false)
    private LocalDate start;

    @Column(name = "event_end", nullable = false)
    private LocalDate end;

    private LocalTime startTime;
    private LocalTime endTime;
    private int budget;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    private String description;

    @ManyToOne
    @JoinColumn(name = "eventType_id", nullable = false)
    private EventType eventType;


    @Column(name = "EventName",unique = true)
    private String text;

    private String color;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private List<Task> tasks;
    public int getProgressPercentage() {
        if (tasks == null || tasks.isEmpty()) {
            return 0;
        }
        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.Completed)
                .count();
        return (int) ((completedTasks * 100) / tasks.size());
    }
//    public void setBudget(int budget) {
//        if (budget > getTotalBudgetUsed()) {
//            throw new IllegalArgumentException("Event's budget cannot be less than the total budget used by tasks.");
//        }
//        this.budget = budget;
//    }


    public int getTotalBudgetUsed() {
        if (tasks == null || tasks.isEmpty()) {
            return 0;
        }
        return tasks.stream()
                .mapToInt(Task::getBudget)
                .sum();
    }


}


