package com.example.group3pkg.repositories;

import com.example.group3pkg.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}