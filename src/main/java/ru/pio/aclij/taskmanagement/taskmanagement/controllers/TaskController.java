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
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    private ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id){
        return ResponseEntity.ok(
                service.getTaskById(id)
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    private List<TaskDto> getTasks(@RequestParam(name = "completed", required = false) boolean completed ){
        return  service.getAllTasks(completed);
    }

    @GetMapping("/forToday")
    @ResponseStatus(HttpStatus.FOUND)
    private List<TaskDto> getTasksForToday(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksForToday(completed);
    }

    @GetMapping("/forWeek")
    @ResponseStatus(HttpStatus.FOUND)
    private List<TaskDto> getTasksForWeek(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksWeek(completed);
    }

    @GetMapping("/forMonth")
    @ResponseStatus(HttpStatus.FOUND)
    private List<TaskDto> getTasksForMonth(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksForMonth(completed);
    }

}
