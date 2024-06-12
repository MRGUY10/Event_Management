package com.example.group3pkg.repositories;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query ("SELECT SUM(t.budget) FROM Task t WHERE t.event = :event")
    Integer sumBudgetByEvent(@Param("event") Event event);
    @Query("SELECT COUNT(t) FROM Task t WHERE t.event.id = :eventId")
    int countByEventId(Long eventId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.event.id = :eventId AND t.status = 'Completed'")
    int countCompletedTasksForEvent(Long eventId);

}