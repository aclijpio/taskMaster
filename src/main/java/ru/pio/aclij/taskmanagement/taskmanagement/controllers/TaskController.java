package ru.pio.aclij.taskmanagement.taskmanagement.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pio.aclij.taskmanagement.taskmanagement.services.TaskService;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)

    private ResponseEntity<List<TaskDto>> getTasks(){
        return ResponseEntity.ok(
                service.getAllTasks()
        );
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    private ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id){
        return ResponseEntity.ok(
                service.getTasksById(id)
        );
    }
}
