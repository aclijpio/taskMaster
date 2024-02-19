package ru.pio.aclij.taskmanagement.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
