package ru.pio.aclij.taskmanagement.taskmanagement.services;

import ru.pio.aclij.taskmanagement.taskmanagement.dtos.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto getTaskById(long id);
    List<TaskDto> getAllTasks(Boolean completed);
    List<TaskDto> getTasksForToday(Boolean completed);
    List<TaskDto> getTasksWeek(Boolean completed);
    List<TaskDto> getTasksForMonth(Boolean completed);
    void deleteTask(Long id);
    void updateTask(TaskDto taskDto);
    TaskDto createTask(TaskDto taskDto);
}
