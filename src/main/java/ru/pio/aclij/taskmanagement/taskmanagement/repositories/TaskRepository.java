package ru.pio.aclij.taskmanagement.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByDate(LocalDate date);
    List<Task> findAllByDateBetween(LocalDate currentDate, LocalDate date);
    List<Task> findAllByCompleted(boolean completed);
    List<Task> findAllByDateAndCompleted(LocalDate date, boolean completed);
    List<Task> findAllByDateBetweenAndCompleted(LocalDate currentDate, LocalDate date, boolean completed);

}
