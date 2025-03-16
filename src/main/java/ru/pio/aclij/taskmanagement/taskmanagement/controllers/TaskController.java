package ru.pio.aclij.taskmanagement.taskmanagement.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pio.aclij.taskmanagement.taskmanagement.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id){
        return ResponseEntity.ok(
                service.getTaskById(id)
        );
    }
    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(name = "completed", required = false) Boolean completed ){
        return  service.getAllTasks(completed);
    }
    @GetMapping("/forToday")
    public List<TaskDto> getTasksForToday(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksForToday(completed);
    }
    @GetMapping("/forWeek")
    public List<TaskDto> getTasksForWeek(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksWeek(completed);
    }
    @GetMapping("/forMonth")
    public List<TaskDto> getTasksForMonth(@RequestParam(name = "completed", required = false) Boolean completed){
        return service.getTasksForMonth(completed);
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto savedDto  = service.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        service.updateTask(taskDto);
        return ResponseEntity.ok(taskDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateCompletedStatus(@PathVariable Long id, @RequestParam(name = "completed") Boolean completed) {
        service.updateTaskCompletedStatus(id, completed);
        return ResponseEntity.ok().build();
    }
}
