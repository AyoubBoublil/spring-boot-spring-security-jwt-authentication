package com.boublil.jwtspringsecurity.service;

import com.boublil.jwtspringsecurity.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    Task saveTask(Task task);
}
