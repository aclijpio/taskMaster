package ru.pio.aclij.taskmanagement.taskmanagement.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TaskDto{
    private String name;
}
