package ru.pio.aclij.taskmanagement.taskmanagement.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class TaskDto{
    private long id;
    private String name;
    private String description;
    private LocalDate date;
    private boolean completed;
}
