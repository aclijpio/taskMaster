package ru.pio.aclij.taskmanagement.taskmanagement.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pio.aclij.taskmanagement.taskmanagement.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.TaskNotFoundException;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.crud.TaskSaveException;
import ru.pio.aclij.taskmanagement.taskmanagement.mappers.TaskMapper;
import ru.pio.aclij.taskmanagement.taskmanagement.repositories.TaskRepository;
import ru.pio.aclij.taskmanagement.taskmanagement.services.TaskService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskDto getTaskById(long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(
                                String.format("Task with id %d not found.", id)
                        ))
        );
    }
    public List<TaskDto> getAllTasks(Boolean completed){
        List<Task> tasks;
        if (completed == null)
            tasks = this.repository.findAll();
        else
            tasks = this.repository.findAllByCompleted(completed);
        return mapper.toDtoList(tasks);
    }
    private List<TaskDto> getTasksByDateRange(LocalDate startDate, LocalDate endDate, Boolean completed) {
        List<Task> tasks;
        if (completed == null) {
            tasks = repository.findAllByDateBetween(startDate, endDate);
        } else {
            tasks = repository.findAllByDateBetweenAndCompleted(startDate, endDate, completed);
        }
        return mapper.toDtoList(tasks);
    }    public List<TaskDto> getTasksForToday(Boolean completed) {
        LocalDate today = LocalDate.now();
        return getTasksByDateRange(today, today, completed);
    }

    public List<TaskDto> getTasksWeek(Boolean completed) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusWeeks(1);
        return getTasksByDateRange(startDate, endDate, completed);
    }

    public List<TaskDto> getTasksForMonth(Boolean completed) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        return getTasksByDateRange(startDate, endDate, completed);
    }

    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(
                    String.format("Task with id %d not found.", id)
            );
        }
        repository.deleteById(id);
    }
    public void updateTask(TaskDto taskDto){
        Task existingTask = repository.findById(taskDto.getId())
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("Task with id %d not found.", taskDto.getId())
                ));

        Task updatedTask = mapper.toEntity(taskDto);
        updatedTask.setId(existingTask.getId());
        repository.save(updatedTask);
    }

    @Transactional
    public TaskDto createTask(TaskDto taskDto){
        try {
            Task task = this.repository.save(mapper.toEntity(taskDto));
            return mapper.toDto(task);
        } catch (DataAccessException e){
            throw new TaskSaveException(
                    String.format("Failed to save task. The entity model is not built correctly. " + taskDto)
            );
        }
    }
}
