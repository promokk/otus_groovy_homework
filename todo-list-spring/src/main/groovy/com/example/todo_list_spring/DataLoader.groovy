package com.example.todo_list_spring

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class DataLoader {
    private final TaskRepository taskRepository
    private final ActionRepository actionRepository

    DataLoader(TaskRepository taskRepository, ActionRepository actionRepository) {
        this.taskRepository = taskRepository
        this.actionRepository = actionRepository
    }

    @PostConstruct
    private void dataLoader() {
//        actionRepository.saveAll(List.of(
//                new Action("Задача 1",
//                        "Это задача 1",
//                        LocalDateTime.of(2024, 12, 10, 01, 00),
//                        LocalDateTime.of(2024, 12, 12, 10, 00)),
//                new Action("Задача 2",
//                        "Это задача 2",
//                        LocalDateTime.of(2024, 12, 14, 12, 00),
//                        LocalDateTime.of(2024, 12, 16, 16, 00))
//        ))

        taskRepository.saveAll(List.of(
                new Task("Задача 1",
                        "Это задача 1",
                        LocalDateTime.of(2024, 12, 10, 10, 00),
                        LocalDateTime.of(2024, 12, 12, 10, 00),
                        [] as List<Action>),
                new Task("Задача 2",
                        "Это задача 2",
                        LocalDateTime.of(2024, 12, 14, 12, 00),
                        LocalDateTime.of(2024, 12, 16, 12, 00),
                        [] as List<Action>)
        ))
    }
}
