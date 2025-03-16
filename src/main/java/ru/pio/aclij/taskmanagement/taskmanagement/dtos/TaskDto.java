package ru.pio.aclij.taskmanagement.taskmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class TaskDto{
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Boolean completed;
}
