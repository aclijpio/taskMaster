package ru.pio.aclij.taskmanagement.taskmanagement.services.mappers;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return TaskDto.builder()
                .name(task.getName())
                .description(task.getDescription())
                .date(task.getDate())
                .completed(task.isCompleted())
                .build();
    }
    public static List<TaskDto> toListDto(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toDto)
                .toList();
    }
    public static Task toModel(TaskDto dto){
        return Task.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .date(dto.getDate())
                .completed(dto.isCompleted())
                .build();
    }
    public static List<Task> toListModel(List<TaskDto> dtos){
        return dtos.stream()
                .map(TaskMapper::toModel)
                .collect(Collectors.toList());
    }


}
