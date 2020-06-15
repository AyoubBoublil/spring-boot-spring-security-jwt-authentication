package com.boublil.jwtspringsecurity.mapper;

import com.boublil.jwtspringsecurity.dto.TaskDto;
import com.boublil.jwtspringsecurity.dto.TaskDto.TaskDtoBuilder;
import com.boublil.jwtspringsecurity.model.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T12:56:08+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto taskToTaskDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.id( task.getId() );
        taskDto.taskName( task.getTaskName() );

        return taskDto.build();
    }

    @Override
    public Task taskDtoToTask(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDto.getId() );
        task.setTaskName( taskDto.getTaskName() );

        return task;
    }

    @Override
    public List<TaskDto> tasksToTaskDtos(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( taskToTaskDto( task ) );
        }

        return list;
    }
}
