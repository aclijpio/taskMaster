package ru.pio.aclij.taskmanagement.taskmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.TaskNotFoundException;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.crud.TaskDeleteException;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.crud.TaskSaveException;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.crud.TaskUpdateException;
import ru.pio.aclij.taskmanagement.taskmanagement.repositories.TaskRepository;
import ru.pio.aclij.taskmanagement.taskmanagement.services.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.services.mappers.TaskMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;



    //Исключение перехвачивается глобальным контроллером ExceptionsController

    public TaskDto getTaskById(long id) {
        return TaskMapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(
                                String.format("Task with id %d not found.", id)
                        ))
        );
    }
    public List<TaskDto> getAllTasks(Boolean completed){
        List<Task> tasks;
        if (completed == null){
            tasks = this.repository.findAll();
        }
        else {
            tasks = this.repository.findAllByCompleted(completed);
        }

        return TaskMapper.toListDto(tasks);
    }

    public List<TaskDto> getTasksForToday(Boolean completed){
        List<Task> tasks;
        if (completed == null){
            tasks = this.repository.findAllByDate(LocalDate.now());
        }
        else {
            tasks = this.repository.findAllByDateAndCompleted(LocalDate.now(), completed);
        }

        return TaskMapper.toListDto(tasks);

    }
    public List<TaskDto> getTasksWeek(Boolean completed){
        LocalDate date = LocalDate.now();
        LocalDate plusWeekDate = date.plusWeeks(1L);
        List<Task> tasks;
        System.out.println(completed);
        if (completed == null) {
            tasks = this.repository.findAllByDateBetween(date, plusWeekDate);
        }
        else {
            tasks = this.repository.findAllByDateBetweenAndCompleted(date, plusWeekDate, completed);
        }
        return TaskMapper.toListDto(tasks);
    }
    public List<TaskDto> getTasksForMonth(Boolean completed){
        LocalDate date = LocalDate.now();
        LocalDate plusMonthDate = date.plusMonths(1L);
        List<Task> tasks;
        if (completed == null) {
            tasks = this.repository.findAllByDateBetween(date, plusMonthDate);
        }
        else {
            tasks = this.repository.findAllByDateBetweenAndCompleted(date, plusMonthDate, completed);
        }

        return TaskMapper.toListDto(tasks);
    }
    public void deleteTask(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataAccessException e){
            throw new TaskDeleteException(
                    String.format("Failed to delete task. Task with id %d not found", id)
            );
        }
    }
    public void updateTask(TaskDto taskDto){
        try {
            this.repository.save(TaskMapper.toModel(taskDto));
        } catch (DataAccessException e){
            throw new TaskUpdateException(
                    String.format("Failed to update task. Task with id %d not found", taskDto.getId())
            );
        }
    }
    public void createTask(TaskDto taskDto){
        try {
            this.repository.save(TaskMapper.toModel(taskDto));
        } catch (DataAccessException e){
            throw new TaskSaveException(
                    String.format("Failed to save task. The entity model is not built correctly. " + taskDto)
            );
        }
    }
}
