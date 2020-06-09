package com.boublil.jwtspringsecurity.controller;

import com.boublil.jwtspringsecurity.model.Task;
import com.boublil.jwtspringsecurity.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final TaskService taskService;

    public RestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task, Authentication authentication) {
        System.out.println(authentication.getName());
        System.out.println(authentication.getPrincipal());
        return taskService.saveTask(task);
    }

}
