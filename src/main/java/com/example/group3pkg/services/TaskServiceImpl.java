package com.example.group3pkg.services;

import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.Task;
import com.example.group3pkg.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) { return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public int getTotalBudgetByEvent(Event event) {
        Integer totalBudget = taskRepository.sumBudgetByEvent(event);
        return totalBudget != null ? totalBudget : 0;
    }
    public double calculateCompletedTasksPercentageForEvent(Long eventId) {
        int totalTasksForEvent = taskRepository.countByEventId(eventId);
        int completedTasksForEvent = taskRepository.countCompletedTasksForEvent(eventId);

        if (totalTasksForEvent == 0) {
            return 0.0; // Avoid division by zero
        }

        return ((double) completedTasksForEvent / totalTasksForEvent) * 100;
    }

}