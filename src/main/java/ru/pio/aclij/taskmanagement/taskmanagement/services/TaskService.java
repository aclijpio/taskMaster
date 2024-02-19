package ru.pio.aclij.taskmanagement.taskmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.TaskNotFoundException;
import ru.pio.aclij.taskmanagement.taskmanagement.repositories.TaskRepository;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.services.mappers.TaskMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<TaskDto> getAllTasks(){
        List<TaskDto> tasks = TaskMapper.toListDto(repository.findAll());
        if (tasks.isEmpty())
            throw new TaskNotFoundException("Task list not found.");
        return tasks;
    }
    public TaskDto getTasksById(long id) {
        return TaskMapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(
                                String.format("Task with id %d not found.", id)
                        ))
        );
    }
}
