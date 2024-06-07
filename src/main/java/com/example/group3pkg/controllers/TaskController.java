package com.example.group3pkg.controllers;

import com.example.group3pkg.models.Contact;
import com.example.group3pkg.models.Event;
import com.example.group3pkg.models.EventType;
import com.example.group3pkg.models.Task;
import com.example.group3pkg.services.ContactService;
import com.example.group3pkg.services.EventService;
import com.example.group3pkg.services.EventTypeService;
import com.example.group3pkg.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TaskController  {

    private TaskService taskService;
    private ContactService contactService;
    private EventService eventService;

    public TaskController(TaskService taskService, ContactService contactService, EventService eventService) {
        super();
        this.taskService = taskService;
        this.contactService = contactService;
        this.eventService = eventService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/Task")
    public String showAllTask(Model model) {
        List<Task> tasks = taskService.getAllTask();
        model.addAttribute("Task", tasks);
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        List<Event> events = eventService.getAllEvent();
        model.addAttribute("events", eventService.getAllEvent());
        model.addAttribute("events", events);
        return "Task"; // Return the HTML template for displaying all events
    }


    @GetMapping("/Tasks/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "/Task";
    }
    @PostMapping("/Tasks/create")
    public String createTask(@ModelAttribute Task task, Model model) {
        Event event = eventService.getEventById(task.getEvent().getId());
        int currentTotalBudget = taskService.getTotalBudgetByEvent(event);

        if (currentTotalBudget + task.getBudget() > event.getBudget()) {
            model.addAttribute("error", "Total task budget cannot be greater than event budget.");
            model.addAttribute("contacts", contactService.getAllContacts());
            model.addAttribute("events", eventService.getAllEvent());
            return "/Task"; // Return to the form with an error message
        }

        taskService.saveTask(task);
        return "redirect:/Task"; // Redirect to a relevant page after saving
    }




    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
            Task task = taskService.getTaskById(id);
            List<Contact> contacts = contactService.getAllContacts();
        List<Event> events = eventService.getAllEvent();
        model.addAttribute("tasks", task);
        model.addAttribute("contacts", contacts);
        model.addAttribute("events", events);
        return "edit_Task"; // Return the HTML template for editing an event
    }
    @GetMapping("/tasks/view/{id}")
    public String showViewForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "View_task"; // Return the HTML template for viewing a task
    }

    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/Task";
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
        existingTask.setStatus(task.getStatus());
        existingTask.setContact(task.getContact());

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
