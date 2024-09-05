package org.example

import java.time.LocalDateTime


static void main(String[] args) {
    InMemoryTask inMemoryTask = new InMemoryTask()
    def toDoList = new ToDoList(inMemoryTask)

    toDoList.add(
            "Задача 1",
            "Это задача",
            LocalDateTime.of(2024, 8, 26, 01, 20),
            LocalDateTime.of(2024, 8, 28, 01, 20)
    )
    toDoList.add(
            "Задача 2",
            "Это задача",
            LocalDateTime.of(2024,8,29,10,00),
            LocalDateTime.of(2024,8,29,12,00)
    )
    toDoList.add(
            "Действие 1-1",
            "Это действие",
            LocalDateTime.of(2024, 8, 26, 20, 00),
            LocalDateTime.of(2024, 8, 26, 21, 00),
            1
    )
    toDoList.add(
            "Действие 1-2",
            "Это действие",
            LocalDateTime.of(2024, 8, 26, 23, 00),
            LocalDateTime.of(2024, 8, 26, 23, 30),
            1
    )

    toDoList.set(1, [title: "Новая Задача 1"])
    toDoList.set(1, 1, [title: "Новое Действие 1-1"])
    toDoList.infoToDoList()
    toDoList.infoToDoList(1)
    toDoList.findTaskByDate(LocalDateTime.of(2024 as int, 8 as int, 26, 00, 00).toLocalDate())
    toDoList.checkEvent()
}
