package ru.pio.aclij.taskmanagement.taskmanagement.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pio.aclij.taskmanagement.taskmanagement.services.TaskService;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    @GetMapping
    private ResponseEntity<List<TaskDto>> getTasks(){
        return ResponseEntity.ok(
                service.getAllTasks()
        );
    }
    @GetMapping("/{id}")
    private ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id){
        return ResponseEntity.ok(
                service.getTasksById(id)
        );
    }
}
