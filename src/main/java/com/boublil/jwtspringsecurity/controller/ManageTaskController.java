package com.boublil.jwtspringsecurity.controller;

import com.boublil.jwtspringsecurity.dto.TaskDto;
import com.boublil.jwtspringsecurity.exception.TaskAlreadyExist;
import com.boublil.jwtspringsecurity.mapper.TaskMapper;
import com.boublil.jwtspringsecurity.model.Task;
import com.boublil.jwtspringsecurity.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ManageTaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public ManageTaskController(TaskService taskService, TaskMapper taskMaxpper) {
        this.taskService = taskService;
        this.taskMapper = taskMaxpper;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        try {
            if (taskDto != null)
                return ResponseEntity.status(HttpStatus.OK).body(taskMapper.taskToTaskDto(taskService.saveTask(taskMapper.taskDtoToTask(taskDto))));
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (TaskAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
