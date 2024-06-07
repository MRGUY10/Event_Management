package com.example.group3pkg.services;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();

    Task saveTask(Task task);

    Task getTaskById(Long id);

    Task updateTask(Task task);
    int getTotalBudgetByEvent(Event event);

    void deleteTaskById(Long id);

}

