package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.TaskDto;
import com.boublil.jwtspringsecurity.dto.UserDto;
import com.boublil.jwtspringsecurity.model.AppUser;
import com.boublil.jwtspringsecurity.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDto taskDto);
    List<TaskDto> tasksToTaskDtos(List<Task> tasks);
}
