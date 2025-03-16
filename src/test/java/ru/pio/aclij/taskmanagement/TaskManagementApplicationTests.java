package ru.pio.aclij.taskmanagement;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.pio.aclij.taskmanagement.taskmanagement.dtos.TaskDto;
import ru.pio.aclij.taskmanagement.taskmanagement.entities.Task;
import ru.pio.aclij.taskmanagement.taskmanagement.exceptions.TaskNotFoundException;
import ru.pio.aclij.taskmanagement.taskmanagement.mappers.TaskMapperImpl;
import ru.pio.aclij.taskmanagement.taskmanagement.repositories.TaskRepository;
import ru.pio.aclij.taskmanagement.taskmanagement.services.impls.TaskServiceImpl;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskManagementApplicationTests {
    @Autowired
    private TaskRepository repository;
    private TaskServiceImpl service;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );


    private static final List<Task> tasks = List.of(
            new Task( 0L,"name", "desc", LocalDate.now(), false),
            new Task(1L,"name321", "desc", LocalDate.now(), true),
            new Task(2L,"name", "desc", LocalDate.now().plusWeeks(1L), true),
            new Task(3L,"name", "desc", LocalDate.now().plusWeeks(2L), false),
            new Task(4L,"name", "desc", LocalDate.now().plusMonths(1L), false),
            new Task(5L, "name", "desc", LocalDate.now().plusMonths(1L), false),
            new Task(6L,"name", "desc", LocalDate.now().plusMonths(2L), false)
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.generate-ddl", () -> true);
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.PostgreSQLDialect");

    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {

        this.service = new TaskServiceImpl(repository, new TaskMapperImpl());
        this.repository.saveAll(tasks);

    }

    @Test
    void getAllTasks(){
        List<TaskDto> dtos = service.getAllTasks(null);
        System.out.println(dtos);
        System.out.println(dtos.size());
        Assertions.assertEquals(service.getAllTasks(null).size(), tasks.size());
    }
    @Test
    void getAllTasksWithFlag(){
        List<TaskDto> dtos = service.getAllTasks(true);
        System.out.println(dtos);
        System.out.println(dtos.size());
        Assertions.assertEquals(service.getAllTasks(true).size(), tasks.stream()
                .filter(Task::isCompleted)
                .count());
    }

    @Test
    void getTodayTasks() {

        List<TaskDto> dtos = service.getTasksForToday(true);
        System.out.println(dtos);
        Assertions.assertEquals(dtos.size(), tasks.stream()
                .filter(task -> task.getDate().isEqual(LocalDate.now()))
                .count());
    }

    @Test
    void getWeekTasksWithFLag() {
        List<TaskDto> dtos = service.getTasksWeek(true);
        System.out.println(dtos);
        Assertions.assertEquals(dtos.size(), tasks.stream()
                .map(Task::getDate)
                .filter(date -> date.isAfter(LocalDate.now().minusDays(1L))
                        && date.isBefore(LocalDate.now().plusWeeks(1L).plusDays(1L)) )
                .count());
    }
    @Test
    void getMonthTasks() {
        List<TaskDto> dtos = service.getTasksForMonth(null);
        System.out.println(dtos);
        Assertions.assertEquals(dtos.size(), 5);
    }

    @Test
    void getById_not() {
        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            service.getTaskById(123213L);
        });
    }
}
