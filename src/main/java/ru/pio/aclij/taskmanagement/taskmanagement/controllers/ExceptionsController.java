package ru.pio.aclij.taskmanagement.taskmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.TaskNotFoundException;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<String> handleException(TaskNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
