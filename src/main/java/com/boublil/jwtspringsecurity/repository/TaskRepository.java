package com.boublil.jwtspringsecurity.repository;

import com.boublil.jwtspringsecurity.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskName(String taskName);
}
