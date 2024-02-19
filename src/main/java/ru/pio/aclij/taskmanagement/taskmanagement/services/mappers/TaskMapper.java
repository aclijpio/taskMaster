package ru.pio.aclij.taskmanagement.taskmanagement.services.mappers;

import org.springframework.stereotype.Component;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;

import java.util.List;
@Component
public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .name(task.getName())
                .build();
    }
    public static List<TaskDto> toListDto(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toDto)
                .toList();
    }
}
