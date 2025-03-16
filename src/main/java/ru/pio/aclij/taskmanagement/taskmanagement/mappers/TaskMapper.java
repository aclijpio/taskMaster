package ru.pio.aclij.taskmanagement.taskmanagement.mappers;

import org.mapstruct.Mapper;
import ru.pio.aclij.taskmanagement.taskmanagement.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;


@Mapper(config = MapStructConfig.class)
public interface TaskMapper extends BaseMapper<Task, TaskDto> {
}
