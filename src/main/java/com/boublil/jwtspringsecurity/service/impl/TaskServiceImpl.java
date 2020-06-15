package com.boublil.jwtspringsecurity.service.impl;

import com.boublil.jwtspringsecurity.exception.TaskAlreadyExist;
import com.boublil.jwtspringsecurity.model.Task;
import com.boublil.jwtspringsecurity.repository.TaskRepository;
import com.boublil.jwtspringsecurity.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) throws TaskAlreadyExist {
        if(taskRepository.findByTaskName(task.getTaskName()) != null) throw new TaskAlreadyExist("Task already exist");
        return taskRepository.save(task);
    }
}
