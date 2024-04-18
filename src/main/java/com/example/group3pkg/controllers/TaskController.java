package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Task;
import com.example.group3pkg.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TaskController  {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/Task")
    public String showAllTask(Model model) {
        List<Task> tasks = taskService.getAllTask();
        model.addAttribute("Task", tasks);
        return "Task"; // Return the HTML template for displaying all events
    }


    @GetMapping("/Tasks/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "/Task";
    }

    @PostMapping("/Tasks/create")
    public String createTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        try {
            taskService.saveTask(task);
            redirectAttributes.addFlashAttribute("message", "Task created successfully!");
            return "redirect:/Task";
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("error", "Failed to create Task: " + e.getMessage());
            return "redirect:/Task";
        }
    }


    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
            Task task = taskService.getTaskById(id);
        model.addAttribute("tasks", task);
        return "edit_Task"; // Return the HTML template for editing an event
    }
    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/Task"; // Redirect to the events page after updating the event
    }

    @PostMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id,
                                @ModelAttribute("task") Task task,
                                Model model) {

        // get student from database by id
        Task existingTask = taskService.getTaskById(id);
        existingTask.setId(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setPriority(task.getPriority());
        existingTask.setAssignee(task.getAssignee());
        existingTask.setStatus(task.getStatus());
        existingTask.setCollaborators(task.getCollaborators());

        // save updated student object
        taskService.updateTask(existingTask);
        return "redirect:/Task";
    }

    // handler method to handle delete student request

    @PostMapping("/Task/delete")
    public String deleteTask(@RequestParam("id") Long eventId) {
        taskService.deleteTaskById(eventId);
        return "redirect:/Task"; // Redirect to the events page after deletion
    }

}
